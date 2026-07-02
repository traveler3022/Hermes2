# Running Hermes2 on Android + Termux

This is the complete technical guide for bringing Hermes2 up on a real Android phone — install, configuration, first connection, and debugging, all in one place.

If you prefer smaller step-by-step guides, follow these in order instead:
[Install in Termux](INSTALL_HERMES_TERMUX.md) → [Setup Wizard](SETUP_HERMES_TERMUX.md) → [Connect the App](GATEWAY_SETUP.md).

The goal is:

```text
Hermes2 Android app
  → starts/controls the Hermes dashboard in Termux
  → connects to ws://127.0.0.1:9119/api/ws
  → uses Gemini / OpenRouter / other configured providers
```

---

## 0. What you need

- An Android phone with **Termux installed from F-Droid** (the Play Store build is abandoned and does not work)
- At least ~500 MB free storage for Hermes Agent and Python/Rust builds
- Network access
- **One** model provider API key, for example:
  - Gemini API key (free tier available at [aistudio.google.com](https://aistudio.google.com))
  - OpenRouter key

The tested reference setup used in this guide:

```text
Gemini as the main backend
DuckDuckGo (ddgs) for free web search
Local terminal backend
No messaging platforms
No browser automation / computer-use on Android
```

---

## 1. Install and prepare Termux

Install Termux from [F-Droid](https://f-droid.org/en/packages/com.termux/), **not** the Play Store.

Then, inside Termux:

```bash
pkg update -y
pkg upgrade -y
pkg install -y git python clang rust make pkg-config libffi openssl ca-certificates curl llvm lld nodejs ripgrep ffmpeg
```

Set the Android/Rust build environment (copy-paste the whole block):

```bash
export ANDROID_API_LEVEL="$(getprop ro.build.version.sdk)"
export CARGO_BUILD_TARGET="$(rustc -Vv | awk '/^host:/ {print $2; exit}')"
export CARGO_HOME="$HOME/.hermes/cargo"
mkdir -p "$CARGO_HOME"
export CARGO_REGISTRIES_CRATES_IO_PROTOCOL=sparse
export CARGO_PROFILE_RELEASE_LTO=false
export CARGO_PROFILE_RELEASE_CODEGEN_UNITS=16
export CARGO_PROFILE_RELEASE_STRIP=none
export CARGO_BUILD_JOBS=1
```

Why these variables matter:

| Variable | Why |
|---|---|
| `ANDROID_API_LEVEL` | Needed by maturin/jiter/pydantic-core builds on Android |
| `CARGO_BUILD_TARGET` | Forces Cargo to build for the native Android target |
| `CARGO_HOME=$HOME/.hermes/cargo` | Avoids broken user Cargo mirror configs such as USTC 404 |
| `CARGO_REGISTRIES_CRATES_IO_PROTOCOL=sparse` | Uses the crates.io sparse index |
| `CARGO_PROFILE_RELEASE_LTO=false` | Avoids a Termux rustc crash during pydantic-core builds |
| `CARGO_BUILD_JOBS=1` | Reduces phone memory/CPU pressure |

> [!NOTE]
> `export` only lasts for the current shell session. If you close Termux and come back mid-install, re-run the block above before continuing.

---

## 2. Allow Hermes2 to control Termux

Hermes2 uses Termux's `RUN_COMMAND` mechanism to start the agent for you. Enable external app commands once:

```bash
mkdir -p ~/.termux
cat > ~/.termux/termux.properties <<'EOF'
allow-external-apps=true
EOF
```

Then **fully restart Termux** (close it from recents, or force-stop it).

Android may also ask the Hermes2 app for the `RUN_COMMAND` permission. Grant it.

---

## 3. Clean old partial installs (only if needed)

Skip this section on a fresh phone.

If a previous failed install left a non-git folder behind:

```bash
if [ -e "$HOME/.hermes/hermes-agent" ] && [ ! -d "$HOME/.hermes/hermes-agent/.git" ]; then
  mv "$HOME/.hermes/hermes-agent" "$HOME/.hermes/hermes-agent.broken-$(date +%Y%m%d-%H%M%S)"
fi
```

If you want a fully fresh install:

```bash
mv "$HOME/.hermes/hermes-agent" "$HOME/.hermes/hermes-agent.backup-$(date +%Y%m%d-%H%M%S)" 2>/dev/null || true
```

---

## 4. Install Hermes Agent

Clone the official upstream repository:

```bash
mkdir -p "$HOME/.hermes"
git clone https://github.com/NousResearch/hermes-agent.git "$HOME/.hermes/hermes-agent"
cd "$HOME/.hermes/hermes-agent"
```

Create a Python virtual environment:

```bash
rm -rf venv
python -m venv venv
source venv/bin/activate
python -m pip install --upgrade pip setuptools wheel
```

Re-export the build environment inside the venv shell (same block as step 1):

```bash
export ANDROID_API_LEVEL="$(getprop ro.build.version.sdk)"
export CARGO_BUILD_TARGET="$(rustc -Vv | awk '/^host:/ {print $2; exit}')"
export CARGO_HOME="$HOME/.hermes/cargo"
mkdir -p "$CARGO_HOME"
export CARGO_REGISTRIES_CRATES_IO_PROTOCOL=sparse
export CARGO_PROFILE_RELEASE_LTO=false
export CARGO_PROFILE_RELEASE_CODEGEN_UNITS=16
export CARGO_PROFILE_RELEASE_STRIP=none
export CARGO_BUILD_JOBS=1
```

Install the Android psutil shim (psutil does not build on Android directly):

```bash
python scripts/install_psutil_android.py --pip "python -m pip"
```

Install the tested Termux profile — **this step takes 5–15 minutes** because Rust code is compiled on the phone. Keep the screen on:

```bash
python -m pip install -e '.[termux]' -c constraints-termux.txt 2>&1 | tee ~/hermes-termux-install.log
```

Install the web/dashboard extra — **required** for the Android app's WebSocket connection:

```bash
python -m pip install -e '.[web]' -c constraints-termux.txt 2>&1 | tee ~/hermes-web-install.log
```

Link the command so `hermes` works in any new shell:

```bash
ln -sf "$PWD/venv/bin/hermes" "$PREFIX/bin/hermes"
```

Verify:

```bash
which hermes
hermes --version
hermes doctor
```

Expected version output example:

```text
Hermes Agent v0.17.0
Python: 3.13.x
OpenAI SDK: 2.24.0
```

> [!NOTE]
> "OpenAI SDK" is just the name of a library Hermes depends on. It does **not** mean you must use OpenAI as your model provider.

---

## 5. Configure your model provider

Put your API key in the Hermes env file:

```bash
nano "$HOME/.hermes/.env"
```

For **Google Gemini** (get a free key at [aistudio.google.com](https://aistudio.google.com)):

```env
GEMINI_API_KEY=YOUR_GEMINI_KEY
```

For **OpenRouter** (get a key at [openrouter.ai/keys](https://openrouter.ai/keys)):

```env
OPENROUTER_API_KEY=YOUR_OPENROUTER_KEY
```

Save the file (`Ctrl+O`, `Enter`, `Ctrl+X` in nano), then tell Hermes which provider and model to use.

For Gemini:

```bash
hermes config set model.provider gemini
hermes config set model.default gemini-2.5-flash   # fast and cheap
# or:
hermes config set model.default gemini-2.5-pro     # smarter, slower
```

For OpenRouter:

```bash
hermes config set model.provider openrouter
hermes config set model.default google/gemini-2.5-flash   # any OpenRouter model id works
```

Check the result:

```bash
cat "$HOME/.hermes/config.yaml"
hermes doctor
```

You should see a line like:

```text
✓ gemini (key configured)
```

---

## 6. Optional: switch providers later

You can switch providers at any time. Add the new provider's key to `~/.hermes/.env` (step 5), then point Hermes at it:

```bash
# switch to OpenRouter
hermes config set model.provider openrouter
hermes config set model.default google/gemini-2.5-flash

# switch back to Gemini
hermes config set model.provider gemini
hermes config set model.default gemini-2.5-flash
```

The app's **Models** screen also lets you switch the active model once connected.

---

## 7. Recommended setup wizard choices

The full walkthrough of every wizard screen is in the **[Setup Wizard guide](SETUP_HERMES_TERMUX.md)**. Short version — if `hermes setup` asks:

### Setup type

Choose **Full setup**. Do not choose Quick Setup unless you want Nous Portal OAuth instead of your own API key.

### Terminal backend

Choose **Local** (or "Keep current (local)"). This is the only backend that works on Android.

### Messaging platforms

Select **none** for now. Messaging platforms are only needed if you want Telegram/Discord/WhatsApp/etc. bots. You can add them later.

### CLI tools

Keep enabled (these work on Android):

```text
Web Search & Scraping
Terminal & Processes
File Operations
Code Execution
Text-to-Speech
Skills
Task Planning
Memory
Session Search
Clarifying Questions
Task Delegation
Cron Jobs
```

Disable for now (unsupported on Android or need extra accounts/APIs):

```text
Browser Automation
Computer Use
Image Generation
Video Generation
X Search
Home Assistant
Spotify
Yuanbao
```

### Web search provider

Choose:

```text
DuckDuckGo (ddgs) — free, no key, search only
```

This lets Hermes search and read web pages. (Browser Automation is different — it controls a real browser and is not needed for normal web search.)

---

## 8. Test Hermes in Termux

After provider setup:

```bash
hermes doctor --fix
hermes doctor
```

Test a model response:

```bash
hermes -q "سلام، فقط در یک جمله بگو با چه مدلی جواب می‌دهی."
```

If the CLI syntax changes in a future version, use:

```bash
hermes chat -q "سلام، تست اتصال مدل"
```

If you got an answer, Hermes itself works — the remaining steps connect the Android app to it.

---

## 9. Start from the Android app

<div dir="rtl">

این مراحل را فقط **یک‌بار**، برای اولین اتصال انجام بده. بعد از آن، هر بار که اپ را باز کنی **خودکار وصل می‌شود**.

</div>

### 9a. Release Termux ports

Stop any manually running dashboard first:

```bash
hermes dashboard --stop
```

Then **leave Termux and force-stop it**:

**Android Settings → Apps → Termux → Force stop**

<div dir="rtl">

> **چرا force-stop؟** موقع نصب، Termux پورت‌هایی را اشغال می‌کند و رها نمی‌کند. با force-stop، تمام پورت‌های باز آزاد می‌شوند تا اپ بتواند یک اتصال تمیز برقرار کند. این فقط **دفعه‌ی اول** لازم است.

</div>

### 9b. Connect from the app

1. Open the **Hermes2** app
2. Tap **`Open runtime host app`** → Termux opens
3. **Come back to Hermes2**
4. Tap **`Start Agent Gateway`**
5. **Wait up to 30 seconds** — the status turns to **✓ Connected**

Why start from the app instead of manually?

The app generates and injects its own `HERMES_DASHBOARD_SESSION_TOKEN`. If you manually start `hermes dashboard` with a different token, the app's WebSocket authentication will fail.

> [!TIP]
> If it doesn't connect within 30 s, **repeat steps 9a and 9b once**. The first handshake occasionally needs a second pass — after that it stays automatic.

### 9c. From the second time onwards

<div dir="rtl">

فقط اپ را باز کن؛ خودش وصل می‌شود. نه Termux لازم است، نه force-stop، نه هیچ کار اضافی.

</div>

After the gateway starts, open the app's **Settings** screen and confirm:

```text
Provider: gemini (or whichever provider you chose)
Model: gemini-2.5-flash or gemini-2.5-pro
```

The **Tools** tab should list the capabilities currently exposed by Hermes.

---

## 10. Debugging

### Gateway logs

```bash
cat "$HOME/.hermes/logs/gateway_stdout.log"
```

Or from the app:

```text
Termux & Agent Connection → Fetch & View Logs
```

### pydantic-core / rustc crash

If you see:

```text
rustc panicked
linker-plugin-lto
```

Make sure these are exported **before** running pip install (see step 1), then retry:

```bash
export CARGO_PROFILE_RELEASE_LTO=false
export CARGO_PROFILE_RELEASE_CODEGEN_UNITS=16
export CARGO_PROFILE_RELEASE_STRIP=none
export CARGO_BUILD_JOBS=1
```

### Cargo mirror 404

If you see:

```text
Updating `ustc` index
unexpected http status code: 404
```

Use a clean Cargo home:

```bash
export CARGO_HOME="$HOME/.hermes/cargo"
mkdir -p "$CARGO_HOME"
export CARGO_REGISTRIES_CRATES_IO_PROTOCOL=sparse
```

### `hermes` command missing

```bash
cd "$HOME/.hermes/hermes-agent"
ln -sf "$PWD/venv/bin/hermes" "$PREFIX/bin/hermes"
which hermes
hermes --version
```

### Disconnects when the screen turns off

Set both **Hermes2** and **Termux** to Unrestricted battery:

**Android Settings → Apps → [app] → Battery → Unrestricted**

---

## 11. What not to do

- **Don't install Termux from the Play Store.** That build is abandoned; only the F-Droid build works.
- **Don't use `pip install -e '.[all]'`.** The `all` profile is not supported on Android — use `'.[termux]'` with `constraints-termux.txt` as shown in step 4.
- **Don't start `hermes dashboard` manually and then expect the app to connect.** The app injects its own session token; always start the gateway from the app (step 9).
- **Don't pick Docker/Modal/SSH terminal backends in the wizard.** Only **Local** works on Android.
- **Don't enable Browser Automation or Computer Use on Android.** They are desktop features and will fail.
- **Don't paste API keys or passwords into the chat.** Everything you type is sent to the model provider.

---

**Smaller guides:** [Install in Termux](INSTALL_HERMES_TERMUX.md) · [Setup Wizard](SETUP_HERMES_TERMUX.md) · [Connect the App](GATEWAY_SETUP.md) · **[Back to README](../README.md)**
