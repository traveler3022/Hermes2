<div dir="rtl">

# نصب Hermes Agent در Termux

راهنمای گام‌به‌گام نصب Hermes Agent روی اندروید از طریق Termux. این **مرحله‌ی ۱ از ۳** برای راه‌اندازی Hermes2 است.

> مستندات رسمی: **[hermes-agent.nousresearch.com/docs/getting-started/termux](https://hermes-agent.nousresearch.com/docs/getting-started/termux)**

</div>

---

## پیش‌نیازها

| پیش‌نیاز | توضیح |
|---|---|
| **گوشی اندروید** | اندروید ۱۰ یا بالاتر |
| **Termux** | نصب‌شده از F-Droid (نه Play Store) |
| **فضای ذخیره‌سازی** | حداقل ۵۰۰ مگابایت خالی |
| **اینترنت** | برای دانلود پکیج‌ها |

> [!WARNING]
> Termux را **فقط از F-Droid** نصب کن. نسخه‌ی Play Store رها شده و کار نمی‌کند.
>
> ← [دانلود Termux از F-Droid](https://f-droid.org/en/packages/com.termux/)

---

## مرحله ۱ — فعال‌کردن دسترسی اپ‌های بیرونی

<div dir="rtl">

این تنظیم لازم است تا اپ Hermes2 بتواند داخل Termux دستور اجرا کند.

</div>

```bash
mkdir -p ~/.termux
echo 'allow-external-apps=true' >> ~/.termux/termux.properties
```

<div dir="rtl">

**بعد Termux را کامل ببند و دوباره باز کن** تا تنظیم اعمال شود.

---

## مرحله ۲ — نصب پکیج‌های سیستمی

</div>

```bash
pkg update -y
pkg install -y git python clang rust make pkg-config libffi openssl ca-certificates curl llvm lld nodejs ripgrep ffmpeg
```

<div dir="rtl">

| پکیج | دلیل |
|---|---|
| python | اجرای هرمس + محیط مجازی (venv) |
| git | دانلود کد |
| clang, rust, make, pkg-config, libffi, openssl | کامپایل وابستگی‌های پایتون |
| ca-certificates, curl | اتصال HTTPS |
| llvm, lld | کامپایل بعضی پکیج‌های Rust |
| nodejs | ابزارهای اختیاری |
| ripgrep | جست‌وجوی سریع فایل |
| ffmpeg | تبدیل صدا/ویدیو |

---

## نصب Hermes Agent

<div dir="rtl">

**دو روش وجود دارد. فقط یکی را انتخاب کن:**

- **روش A (خودکار):** یک دستور، همه‌چیز را خودش نصب می‌کند. **اول این را امتحان کن.**
- **روش B (دستی):** قدم‌به‌قدم و شفاف. اگر روش A خطا داد، سراغ این بیا.

---

### روش A — نصب خودکار (ساده‌تر)

</div>

```bash
curl -fsSL https://hermes-agent.nousresearch.com/install.sh | bash
```

<div dir="rtl">

نصب‌کننده‌ی خودکار:
- پکیج‌های سیستمی را نصب می‌کند
- محیط مجازی پایتون می‌سازد
- اول `.[termux-all]` را امتحان می‌کند، اگر نشد `.[termux]`، اگر نشد نصب پایه
- دستور `hermes` را در PATH قرار می‌دهد

> ⏱️ نصب اول **۵ تا ۱۵ دقیقه** طول می‌کشد (کد Rust روی گوشی کامپایل می‌شود). صفحه را روشن نگه دار.

اگر نصب‌کننده خطا داد، **روش B** را امتحان کن.

اگر نصب موفق بود، مستقیم برو به **[مرحله ۳ — بررسی نصب](#مرحله-۳--بررسی-نصب)**.

---

### روش B — نصب دستی (کامل و شفاف)

#### ب-۱. تنظیم محیط کامپایل

</div>

```bash
export ANDROID_API_LEVEL="$(getprop ro.build.version.sdk)"
```

<div dir="rtl">

> [!NOTE]
> `ANDROID_API_LEVEL` برای کامپایل پکیج‌های Rust مثل `jiter` و `pydantic-core` لازم است.

اگر موقع نصب خطای Rust دیدی، این متغیرها را هم تنظیم کن:

</div>

```bash
export CARGO_BUILD_TARGET="$(rustc -Vv | awk '/^host:/ {print $2; exit}')"
export CARGO_HOME="$HOME/.hermes/cargo"
mkdir -p "$CARGO_HOME"
export CARGO_REGISTRIES_CRATES_IO_PROTOCOL=sparse
export CARGO_PROFILE_RELEASE_LTO=false
export CARGO_PROFILE_RELEASE_CODEGEN_UNITS=16
export CARGO_PROFILE_RELEASE_STRIP=none
export CARGO_BUILD_JOBS=1
```

<div dir="rtl">

| متغیر | دلیل |
|---|---|
| `CARGO_HOME` | جلوگیری از خطای mirror مثل USTC 404 |
| `CARGO_REGISTRIES_CRATES_IO_PROTOCOL=sparse` | استفاده از ایندکس sparse |
| `CARGO_PROFILE_RELEASE_LTO=false` | جلوگیری از crash کامپایلر Rust |
| `CARGO_BUILD_JOBS=1` | کاهش فشار حافظه/CPU روی گوشی |

> [!NOTE]
> دستورهای `export` فقط تا وقتی همین شل باز است اعتبار دارند. اگر Termux را بستی و برگشتی، دوباره اجرایشان کن.

#### ب-۲. دانلود کد

</div>

```bash
git clone https://github.com/NousResearch/hermes-agent.git
cd hermes-agent
```

#### ب-۳. ساخت محیط مجازی پایتون

```bash
python -m venv venv
source venv/bin/activate
export ANDROID_API_LEVEL="$(getprop ro.build.version.sdk)"
python -m pip install --upgrade pip setuptools wheel
```

#### ب-۴. نصب پروفایل Termux

```bash
python -m pip install -e '.[termux]' -c constraints-termux.txt
```

<div dir="rtl">

> ⏱️ این مرحله **۵ تا ۱۵ دقیقه** طول می‌کشد. صفحه را روشن نگه دار.

#### ب-۵. نصب psutil برای اندروید

psutil روی اندروید مستقیم نصب نمی‌شود و اسکریپت جایگزین (shim) لازم دارد:

</div>

```bash
python scripts/install_psutil_android.py --pip "python -m pip"
```

<div dir="rtl">

#### ب-۶. نصب وب‌سرور dashboard (برای اتصال اپ)

اپ Hermes2 از طریق WebSocket به dashboard هرمس وصل می‌شود، پس این بخش هم لازم است:

</div>

```bash
python -m pip install -e '.[web]' -c constraints-termux.txt
```

> [!NOTE]
> <div dir="rtl">مستندات رسمی Termux این دو مرحله (ب-۵ و ب-۶) را ندارد چون فقط برای CLI نوشته شده. ولی برای اتصال اپ Hermes2 <b>حتماً لازم‌اند</b>.</div>

<div dir="rtl">

#### ب-۷. قراردادن hermes در PATH

</div>

```bash
ln -sf "$PWD/venv/bin/hermes" "$PREFIX/bin/hermes"
```

<div dir="rtl">

`$PREFIX/bin` در Termux از قبل داخل PATH هست، پس از این به بعد دستور `hermes` در هر شل جدید کار می‌کند.

---

## مرحله ۳ — بررسی نصب

</div>

```bash
hermes --version
hermes doctor
```

<div dir="rtl">

خروجی مورد انتظار:

</div>

```text
Hermes Agent v0.17.x
Python: 3.13.x
```

<div dir="rtl">

> [!NOTE]
> اگر `hermes doctor` خطا نشان داد، `hermes doctor --fix` را اجرا کن و دوباره چک کن.

---

## مرحله ۴ — تنظیم کلید API

قبل از اولین اجرا، کلید API ارائه‌دهنده‌ی مدلت را در فایل env هرمس بگذار:

</div>

```bash
nano ~/.hermes/.env
```

<div dir="rtl">

برای **Gemini** (کلید رایگان از [aistudio.google.com](https://aistudio.google.com)):

</div>

```env
GEMINI_API_KEY=YOUR_GEMINI_KEY
```

<div dir="rtl">

برای **OpenRouter** (کلید از [openrouter.ai/keys](https://openrouter.ai/keys)):

</div>

```env
OPENROUTER_API_KEY=YOUR_OPENROUTER_KEY
```

<div dir="rtl">

ذخیره کن: `Ctrl+O` بعد `Enter` بعد `Ctrl+X`.

سپس به هرمس بگو از کدام provider و مدل استفاده کند (مثال برای Gemini):

</div>

```bash
hermes config set model.provider gemini
hermes config set model.default gemini-2.5-flash
```

<div dir="rtl">

---

## مرحله ۵ — اولین اجرا

</div>

```bash
hermes
```

<div dir="rtl">

بار اول، ویزارد راه‌اندازی اجرا می‌شود — گزینه‌های پیشنهادی هر صفحه را در راهنمای بعدی نوشته‌ایم:

**← ادامه بده: [راهنمای ویزارد راه‌اندازی](SETUP_HERMES_TERMUX.md)**

اگر قبلاً تنظیم کرده باشی، مستقیم وارد چت می‌شوی.

---

## مرحله ۶ — نصب ابزارهای Node (اختیاری)

نصب Termux عمداً ابزارهای Node/browser را رد می‌کند. اگر می‌خواهی browser tool را امتحان کنی:

</div>

```bash
pkg install nodejs-lts
npm install
```

<div dir="rtl">

> [!NOTE]
> ابزارهای browser/WhatsApp روی اندروید **تجربی** هستند.

---

## عیب‌یابی

### `hermes: command not found`

</div>

```bash
cd hermes-agent
ln -sf "$PWD/venv/bin/hermes" "$PREFIX/bin/hermes"
which hermes
```

<div dir="rtl">

### خطای نصب `.[all]`

پروفایل `.[all]` روی اندروید پشتیبانی نمی‌شود. از پروفایل Termux استفاده کن:

</div>

```bash
python -m pip install -e '.[termux]' -c constraints-termux.txt
```

<div dir="rtl">

### Termux در پس‌زمینه کشته می‌شود

تنظیمات اندروید ← برنامه‌ها ← Termux ← باتری ← **بدون محدودیت**

و داخل Termux:

</div>

```bash
termux-wake-lock
```

---

<div dir="rtl">

> [!NOTE]
> اندروید برای هرمس پلتفرم **«Tier 2»** است: Browser Automation، Computer Use، voice transcription و Docker روی اندروید کار نمی‌کنند. بقیه‌ی امکانات کار می‌کنند.

---

**← مرحله‌ی بعد: [ویزارد راه‌اندازی](SETUP_HERMES_TERMUX.md)** · بعدش: [اتصال اپ](GATEWAY_SETUP.md) · مرجع کامل: [راهنمای فنی](RUNNING_ON_ANDROID_TERMUX.md)
</div>
