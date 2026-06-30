<div align="center">

# ⬡ Hermes2

### همراه نیتیو اندروید برای **[Hermes Agent](https://github.com/NousResearch/hermes-agent)**

*یک اتاق فرمان متمرکز روی اندروید برای ایجنت هوش مصنوعی خودت — کاملاً روی گوشی.*

<br>

![Material 3](https://img.shields.io/badge/Material_3-6750A4?style=for-the-badge&logo=materialdesign&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)

[![Build](https://github.com/traveler3022/Hermes2/actions/workflows/build-apk.yml/badge.svg)](https://github.com/traveler3022/Hermes2/actions/workflows/build-apk.yml)
[![Download APK](https://img.shields.io/badge/⬇_Download-APK-6750A4?style=flat-square&logo=android&logoColor=white)](https://github.com/traveler3022/Hermes2/releases/tag/debug-latest)
[![License: MIT](https://img.shields.io/badge/License-MIT-00BCD4?style=flat-square)](LICENSE)

<br>

`آرام` · `فنی` · `خوانا` · `سریع` · `قابل‌اعتماد`

<br>

[English 🇬🇧](README.md) · **فارسی**

</div>

---

<div align="center">

```
╭──────────────────────────────────────────────╮
│   Hermes2 App  ◄──── ws://127.0.0.1 ────►  Hermes Agent   │
│   (Material 3 UI)      loopback only        (Termux)      │
╰──────────────────────────────────────────────╯
```

**بدون واسطه‌ی ابری · بدون حساب کاربری · بدون تله‌متری**

</div>

---

<div dir="rtl">

## ⬡ این چیه؟

> **Hermes Agent** یک ایجنت هوش مصنوعی متن‌باز و قدرتمند از [Nous Research](https://github.com/NousResearch/hermes-agent) است که معمولاً روی دسکتاپ اجرا می‌شود.
>
> **Hermes2** اولین پورت نیتیو **اندروید** آن است — همان ایجنت را با یک رابط کاربری درست و حسابی **Material 3** به گوشی‌ات می‌آورد، به‌جای تایپ‌کردن دستور در ترمینال.

ایجنت کار را انجام می‌دهد — گفت‌وگو، اجرای ابزار، نوشتن کد. این اپ یک خانه‌ی راحت روی اندروید برایش می‌سازد: یک صفحه‌ی چت واقعی، تنظیمات، سشن‌ها، مهارت‌ها و اعلان‌های تأیید ابزار.

> [!IMPORTANT]
> **این یک ابزار حرفه‌ای است، نه یک اپ چت معمولی.** Hermes Agent می‌تواند دستورهای واقعی روی دستگاهت اجرا کند (داخل Termux). اگر فقط می‌خواهی با هوش مصنوعی گپ بزنی، یک اپ چت معمولی مناسب‌تر است. اگر یک ایجنت واقعی می‌خواهی که روی گوشی‌ات *کار انجام بدهد* — این برای توست. **لطفاً قبل از نصب، [حریم خصوصی و امنیت](#-حریم-خصوصی-و-امنیت--اول-بخوان) را بخوان.**

---

## ⬡ قابلیت‌ها

یک اتاق فرمان نیتیو با Material 3 — نه webview، نه پوسته‌ی ترمینال.

</div>

<table>
<tr>
<td width="50%" valign="top">

#### 💬 گفت‌وگو
- **استریم زنده‌ی** پاسخ‌ها، توکن‌به‌توکن
- **نمایش استدلال** — *فکرکردن* مدل را قبل از پاسخ ببین
- **کارت‌های فراخوانی ابزار** — هر دستوری که ایجنت اجرا می‌کند، با خروجی جمع‌شونده
- **کارت‌های زیرایجنت** — اجرای ایجنت‌های تو‌در‌تو به‌صورت inline
- **دستورهای اسلش** — `/help`، `/model`، `/config`، … مستقیم از کاتالوگ gateway
- **منوی لمس‌طولانی** — کپی، تلاش مجدد، بازتولید
- **اسکرول خودکار** که می‌داند کِی بایستد

</td>
<td width="50%" valign="top">

#### 🗂️ سشن‌ها و کنترل
- **کشوی سشن** — جست‌وجو · مرتب‌سازی · سنجاق · تغییر‌نام · حذف
- **ادامه‌ی** هر گفت‌وگوی قبلی، با بازیابی کامل تاریخچه
- صفحه‌ی **Runtime Setup** — تشخیص، نصب، استارت/استاپ gateway
- **تأیید ابزار** به‌صورت اعلان اندروید — **Approve / Deny** قبل از هر اجرا
- مدیریت **تنظیمات و مدل** از داخل اپ
- صفحه‌های **Skills، Cron و Platforms** برای امکانات جانبی ایجنت

</td>
</tr>
<tr>
<td width="50%" valign="top">

#### 🎨 ظاهر و حس
- **۶ تم رنگی** — Hermes · Blue Eye · Mocha · Midnight · Indigo Pro · Carbon
- حالت‌های **روشن · تاریک · سیستم**
- طراحی کامل **Material 3**، آماده‌ی dynamic color

</td>
<td width="50%" valign="top">

#### 🔒 ساخته‌شده برای اعتماد
- ارتباط اپ ↔ ایجنت **۱۰۰٪ محلی** (`127.0.0.1`)
- **بدون حساب · بدون تله‌متری · بدون واسطه‌ی ابری**
- **onboarding دوزبانه** — انگلیسی و فارسی
- یک foreground service، gateway را در پس‌زمینه زنده نگه می‌دارد

</td>
</tr>
</table>

<div dir="rtl">

---

## ⬡ چطور کار می‌کند

</div>

```
┌─────────────────────────┐
│   Hermes2 Android App   │   Material 3 UI
└────────────┬────────────┘
             │  WebSocket · ws://127.0.0.1:9119/api/ws
             │  loopback — هرگز گوشی را ترک نمی‌کند
┌────────────▼────────────┐
│   Hermes Agent (Python) │   داخل Termux
└────────────┬────────────┘
             │  HTTPS
┌────────────▼────────────┐
│   ارائه‌دهنده‌ی مدل تو   │   MiMo · Gemini · OpenRouter · …
└─────────────────────────┘
```

<div dir="rtl">

اپ از طریق یک WebSocket **محلی** روی `127.0.0.1` با Hermes Agent حرف می‌زند — این ارتباط کاملاً روی دستگاهت می‌ماند. Hermes منطق هوش مصنوعی را اجرا می‌کند؛ اپ اندروید نمای نیتیو است.

---

## ⬡ حریم خصوصی و امنیت — اول بخوان

> [!WARNING]
> این بخش مهم است. لطفاً قبل از نصب کامل بخوانش.

#### 🟢 روی گوشی‌ات می‌ماند

| | |
|---|---|
| **کلید API تو** | داخل تنظیمات خود Hermes (`~/.hermes/.env`) در Termux، روی دستگاهت ذخیره می‌شود. این اپ کلیدت را به **هیچ** سروری از ما نمی‌فرستد. بدون حساب، بدون بک‌اند، بدون تله‌متری. |
| **ارتباط اپ ↔ ایجنت** | روی `127.0.0.1` (loopback) اجرا می‌شود. هرگز دستگاه را ترک نمی‌کند. |

#### 🟠 از گوشی‌ات خارج می‌شود

| | |
|---|---|
| **پیام‌هایت به هوش مصنوعی** | به **ارائه‌دهنده‌ی مدلی که انتخاب کردی** می‌رود — هر API هوش مصنوعی همین‌طور کار می‌کند. MiMo بزنی → شیائومی می‌بیند؛ Gemini بزنی → گوگل می‌بیند. |
| **قاعده‌ی کلی** | چیزی نفرست که نمی‌خواهی ارائه‌دهنده ببیند. مراقب ارائه‌دهنده‌هایی باش که از قوانین داده‌شان مطمئن نیستی. |

#### 🛡️ ایجنت روی دستگاهت چه کاری می‌تواند بکند

Hermes می‌تواند دستورهای شل را داخل Termux اجرا کند. به لطف sandbox اندروید، روی گوشی **روت‌نشده** این کار به حافظه‌ی خود Termux محدود است — ایجنت **نمی‌تواند** به داده‌ی اپ‌های دیگر یا فایل‌های سیستمی برسد. این یک محافظت واقعی و توکار است.

> [!CAUTION]
> **برای اجرای این، گوشی‌ات را روت نکن.** روت‌کردن همان sandbox ای را که ایجنت را محصور نگه می‌دارد از بین می‌برد.

#### ✅ تأیید ابزار — روشن نگهش دار

Hermes قبل از اجرای دستورهایی که خطرناک می‌داند، می‌پرسد. درخواست‌ها به‌صورت اعلان اندروید با دکمه‌های **Approve / Deny** ظاهر می‌شوند. **تأیید را روشن نگه دار**، مخصوصاً وقتی تازه‌کاری — این خط دفاعی است که یک دستور مخرب را قبل از اجرا متوقف می‌کند. در شک، **Deny** بزن، بعد از ایجنت بپرس می‌خواست چه کند.

> [!NOTE]
> این اپ **همان‌طور که هست** تحت لایسنس MIT ارائه می‌شود. مسئولیت کلیدهایی که استفاده می‌کنی، ارائه‌دهنده‌ای که انتخاب می‌کنی و دستورهایی که تأیید می‌کنی با خودت است.

---

## ⬡ دانلود

</div>

<div align="center">

### [⬇ دانلود آخرین APK](https://github.com/traveler3022/Hermes2/releases/download/debug-latest/app-debug.apk)

*بیلد debug همیشه‌تازه — با هر push خودکار دوباره ساخته می‌شود.*

</div>

<div dir="rtl">

> [!NOTE]
> این یک بیلد **debug** است (بدون امضا، برای تست). فقط اگر به منبع اعتماد داری و نکات امنیتی بالا را فهمیده‌ای نصبش کن.

---

## ⬡ نصب

به دو تکه نیاز داری: **Termux** (ایجنت را اجرا می‌کند) و **اپ Hermes2** (رابط کاربری). اول Termux را آماده کن.

### ① نصب Termux

Termux را از F-Droid نصب کن → **[f-droid.org/packages/com.termux](https://f-droid.org/en/packages/com.termux/)**

> [!WARNING]
> Termux را **از Play Store نصب نکن** — آن نسخه رهاشده است و کار نمی‌کند.

### ② اجازه‌ی اپ‌های بیرونی در Termux

تا Hermes2 بتواند در Termux دستور اجرا کند:

</div>

```bash
mkdir -p ~/.termux
echo 'allow-external-apps=true' >> ~/.termux/termux.properties
```

<div dir="rtl">

بعد **Termux را force-stop کن و دوباره باز کن** تا اعمال شود.

### ③ نصب Hermes Agent

نصب‌کننده‌ی رسمی را در Termux اجرا کن:

</div>

```bash
pkg update -y && pkg install -y curl
curl -fsSL https://hermes-agent.nousresearch.com/install.sh | bash
```

<div dir="rtl">

> ⏱️ نصب اول **۵ تا ۱۵ دقیقه** طول می‌کشد (کریت‌های Rust را برای ARM کامپایل می‌کند). صفحه را روشن نگه دار.

بررسی:

</div>

```bash
hermes --version
hermes doctor
```

<div dir="rtl">

> [!NOTE]
> **اندروید برای Hermes Agent یک پلتفرم «Tier 2» است** (طبق [راهنمای رسمی Termux](https://hermes-agent.nousresearch.com/docs/getting-started/termux)). چند قابلیت مخصوص دسکتاپ اینجا عمداً خاموش‌اند: **تبدیل گفتار به متن** (`faster-whisper`)، **بک‌اند Docker** و **اتوماسیون مرورگر**. هر چیزی که اپ Hermes2 نشان می‌دهد — چت، ابزار، سشن‌ها، مهارت‌ها — کار می‌کند.
>
> اگر نصب‌کننده‌ی یک‌خطی روی وابستگی‌ها شکست خورد، اول پکیج‌های پایه را دستی نصب کن، بعد دوباره اجرا کن:
> ```bash
> pkg update && pkg install -y git python clang rust make pkg-config libffi openssl nodejs ripgrep ffmpeg
> ```

### ④ نصب اپ

۱. APK را از لینک بالا دانلود کن.
۲. **«نصب از منابع ناشناس»** را در تنظیمات اندروید فعال کن.
۳. APK را باز کن و نصب کن.

### ⑤ اولین اتصال gateway

اولین اتصال به یک دست‌دادن کوتاه و **یک‌باره** نیاز دارد تا gateway را تمیز از نصب‌کننده به اپ تحویل دهد. بعد از این، **هر اجرای بعدی خودکار وصل می‌شود** — دیگر این مراحل را تکرار نمی‌کنی.

> [!IMPORTANT]
> این دنباله را یک‌بار، به‌ترتیب انجام بده:

**در Termux** *(یک خط‌فرمان تازه درست بعد از پایان setup باز می‌شود)*

</div>

```bash
hermes dashboard --stop
```

<div dir="rtl">

بعد **از Termux خارج شو و force-stop ش کن**:
**تنظیمات اندروید → برنامه‌ها → Termux → Force stop**

> *چرا force-stop؟* سشن نصب، **پورت gateway را همچنان اشغال** نگه می‌دارد. force-stop آن را آزاد می‌کند تا اپ بتواند در استارت بعدی یک اتصال تمیز بگیرد. این **فقط** برای اولین setup لازم است.

**در اپ Hermes2 — صفحه‌ی `Termux & Agent Setup`**

۱. روی **`Open runtime host app`** بزن → Termux باز می‌شود
۲. به Hermes2 برگرد
۳. روی **`Start Agent Gateway (Termux)`** بزن
۴. **تا حدود ۳۰ ثانیه صبر کن** — کارت وضعیت به **✓ Connected** تغییر می‌کند
۵. شروع به چت کن

> [!TIP]
> اگر تا ~۳۰ ثانیه وصل نشد، فقط **مراحل بالا را تکرار کن** (`Open runtime host app` → برگشت به اپ → `Start Agent Gateway`). دست‌دادن اول گاهی به پاس دوم نیاز دارد — بعدش جا می‌افتد و خودکار می‌ماند.

---

## ⬡ تنظیم مدل

کلید را یک‌بار، داخل Termux تنظیم کن. دو گزینه‌ی کم‌هزینه که روی گوشی خوب کار می‌کنند:

</div>

<details open>
<summary><b>گزینه A — Xiaomi MiMo</b> · پیشنهادی، کم‌هزینه و سریع</summary>

<br>

<div dir="rtl">

در Termux: `nano ~/.hermes/.env` و اضافه کن:

</div>

```env
XIAOMI_API_KEY=your_key_here
XIAOMI_BASE_URL=https://api.xiaomimimo.com/v1
```

<div dir="rtl">

ذخیره کن (`Ctrl+O`، `Enter`، `Ctrl+X`)، بعد:

</div>

```bash
hermes config set model.provider xiaomi
hermes config set model.default mimo-v2.5-free
```

<div dir="rtl">

اگر یکی در دسترس نبود، اسم مدل‌ها را به‌ترتیب امتحان کن:
`mimo-v2.5-free` → `mimo-v2.5` → `mimo-v2.5-pro`

</div>

</details>

<details>
<summary><b>گزینه B — Google Gemini</b></summary>

<br>

<div dir="rtl">

در Termux: `nano ~/.hermes/.env` و اضافه کن:

</div>

```env
GEMINI_API_KEY=your_key_here
```

<div dir="rtl">

بعد:

</div>

```bash
hermes config set model.provider gemini
hermes config set model.default gemini-2.5-flash
```

</details>

<div dir="rtl">

> [!CAUTION]
> **کلیدت را خصوصی نگه دار.** هر کسی کلیدت را داشته باشد می‌تواند اعتبارت را خرج کند. هرگز توی اسکرین‌شات، چت یا issueهای عمومی paste نکن. اگر لو رفت → در داشبورد ارائه‌دهنده باطلش کن و یکی تازه بساز.

---

## ⬡ کنترل هزینه

بیشتر ارائه‌دهنده‌ها به‌ازای توکن صورت‌حساب می‌دهند. برای جلوگیری از غافلگیری:

- 💳 **یک سقف خرج** در داشبورد ارائه‌دهنده‌ات تنظیم کن *(MiMo، گوگل، OpenRouter همه پشتیبانی می‌کنند)* — بهترین محافظتت.
- 🆓 با یک **مدل رایگان/ارزان** مثل `mimo-v2.5-free` شروع کن تا با رفتار ایجنت آشنا شوی.
- 👀 چند سشن اول را تماشا کن تا حس کنی هر کار چقدر توکن می‌خورد.

---

## ⬡ عیب‌یابی

</div>

<details>
<summary><b>اپ تا ابد روی «Connecting…» گیر کرده</b></summary>

<br>

<div dir="rtl">

استارت سرد ۳۰ تا ۹۰ ثانیه طول می‌کشد. صبر کن، بعد لاگ‌ها را در Termux ببین:

</div>

```bash
cat ~/.hermes/logs/gateway_stdout.log
```

<div dir="rtl">

اگر Termux می‌گوید Hermes در حال اجراست ولی اپ وصل نمی‌شود: یک‌بار Termux را force-stop و دوباره باز کن (تا `allow-external-apps=true` اعمال شود)، بعد دوباره gateway را استارت کن.

</div>

</details>

<details>
<summary><b>بیلد <code>jiter</code> / <code>pydantic-core</code> موقع نصب شکست می‌خورد</b></summary>

<br>

<div dir="rtl">

این‌ها را در Termux **قبل از** اجرای نصب‌کننده تنظیم کن، بعد دوباره امتحان کن:

</div>

```bash
export CARGO_HOME="$HOME/.hermes/cargo"
export CARGO_REGISTRIES_CRATES_IO_PROTOCOL=sparse
export CARGO_PROFILE_RELEASE_LTO=false
export CARGO_PROFILE_RELEASE_CODEGEN_UNITS=16
export CARGO_BUILD_JOBS=1
```

</details>

<details>
<summary><b><code>hermes: command not found</code></b></summary>

<br>

```bash
cd ~/.hermes/hermes-agent
ln -sf "$PWD/venv/bin/hermes" "$PREFIX/bin/hermes"
which hermes
```

</details>

<details>
<summary><b>وقتی صفحه خاموش می‌شود قطع می‌شود</b></summary>

<br>

<div dir="rtl">

یک foreground service، gateway را زنده نگه می‌دارد، ولی بهینه‌سازهای باتری تهاجمی هنوز می‌توانند بکشندش:

- **تنظیمات → برنامه‌ها → Hermes2 → باتری → بدون محدودیت**
- اگر ادامه داشت، همین را برای **Termux** هم انجام بده.

</div>

</details>

<div dir="rtl">

---

## ⬡ معماری

</div>

```
UI (Compose) ─► ViewModel ─► HermesRuntime  (interface)
                         └─► GatewayClient   (interface)
                                  │ Hilt DI
                                  ▼
                         TermuxBridge        (impl)
                         OkHttpGatewayClient (impl)
                                  ▼
                         Hermes Agent in Termux
```

<div dir="rtl">

تمام کد UI و ViewModel فقط به **interface** ها وابسته است. عوض‌کردن runtime مبتنی بر Termux با یک runtime پایتون توکار *(ADR-009)* فقط نیاز به تغییر ماژول DI دارد — بدون هیچ تغییری در UI یا ViewModel. ارتباط ایجنت، JSON-RPC روی یک WebSocket محلی است.

📂 مستندات طراحی در [`docs/`](docs/):
[`RUNNING_ON_ANDROID_TERMUX.md`](docs/RUNNING_ON_ANDROID_TERMUX.md) · [`MATERIAL3_UI_GUIDE.md`](docs/MATERIAL3_UI_GUIDE.md)

---

## ⬡ ساخت از سورس

</div>

```bash
git clone https://github.com/traveler3022/Hermes2.git
cd Hermes2

# Debug APK
bash ./gradlew :app:assembleDebug

# Unit tests
bash ./gradlew :app:testDebugUnitTest
```

<div dir="rtl">

**نیازمندی‌ها:** JDK 17 · Android SDK 35 · Android Studio Ladybug+
**خروجی:** `app/build/outputs/apk/debug/`

---

## ⬡ مشارکت

issueها و PRها خوش‌آمدند. این یک پورت مستقل و جامعه‌محور است — نه محصول رسمی Nous Research. موقع گزارش باگ، **نسخه‌ی اندروید**، **مدل گوشی** و خطوط مرتبط از `~/.hermes/logs/gateway_stdout.log` را بگذار.

---

## ⬡ لایسنس

**MIT** — به [LICENSE](LICENSE) نگاه کن.

<sub>پروژه‌ی مستقل · وابسته به Nous Research نیست و مورد تأیید آن‌ها نیست. «Hermes Agent» متعلق به نویسندگان آن است.</sub>

</div>

<div align="center">
<br>

**⬡ ساخته‌شده برای اندروید · با قدرت Hermes Agent ⬡**

</div>
