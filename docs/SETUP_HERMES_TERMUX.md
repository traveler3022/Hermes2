<div dir="rtl">

# راه‌اندازی اولیه Hermes در Termux

بعد از [نصب هرمس](INSTALL_HERMES_TERMUX.md)، اولین اجرای `hermes` یک ویزارد راه‌اندازی نشانت می‌دهد. این راهنما **هر صفحه‌ی ویزارد و گزینه‌ی پیشنهادی** آن را توضیح می‌دهد. این **مرحله‌ی ۲ از ۳** برای راه‌اندازی Hermes2 است.

اگر عجله داری، [جدول خلاصه‌ی انتهای صفحه](#خلاصهی-گزینههای-پیشنهادی) را ببین.

</div>

---

## Step 1 — How would you like to set up Hermes?

<div dir="rtl">

اولین سؤال: می‌خواهی هرمس را چطور تنظیم کنی؟

</div>

```
How would you like to set up Hermes?
↑↓ navigate  ENTER/SPACE select  ESC cancel

  ( ) Quick Setup (Nous Portal) – free OAuth login, no API keys, mode...
● ( ) Full setup – configure every provider, tool & option yourself (...
  ( ) Blank Slate – everything off except the bare minimum; opt in to...
```

<div dir="rtl">

| گزینه | توضیح |
|---|---|
| **Quick Setup** | ورود با OAuth از طریق Nous Portal؛ بدون نیاز به کلید API. ساده است، ولی به حساب Nous Portal وابسته می‌شوی. |
| **Full Setup** | همه‌چیز را خودت تنظیم می‌کنی — provider، ابزارها و جست‌وجو. مسیر تست‌شده‌ی این راهنما همین است. |
| **Blank Slate** | همه‌چیز خاموش به‌جز حداقل‌ها؛ بعداً خودت فعال می‌کنی. |

**← `Full Setup` را انتخاب کن و `ENTER` بزن.** این راهنما بر اساس همین گزینه نوشته شده، چون با کلید API خودت کار می‌کند و کنترل کامل به تو می‌دهد. (Quick Setup را فقط وقتی انتخاب کن که بخواهی با حساب Nous Portal و بدون کلید API کار کنی.)

---

## Step 2 — Select provider

انتخاب ارائه‌دهنده‌ی مدل هوش مصنوعی:

</div>

```
Select provider:
↓↑ navigate  ENTER/SPACE select  ESC cancel

→ Nous Portal
  OpenRouter
  Anthropic
  OpenAI
  Gemini
  Google AI Studio
  DeepSeek
  ...
  Custom endpoint (enter URL manually)
  Leave unchanged
```

<div dir="rtl">

| ارائه‌دهنده | توضیح |
|---|---|
| **Gemini** | ارزان و سریع، مناسب اندروید. نیاز به `GEMINI_API_KEY` |
| **Google AI Studio** | Gemini مستقیم. نیاز به `GEMINI_API_KEY` |
| **OpenRouter** | تجمیع‌کننده‌ی pay-per-use با صدها مدل. نیاز به `OPENROUTER_API_KEY` |
| **Nous Portal** | ورود OAuth، بیش از ۳۰۰ مدل |

**← برای اندروید، `Gemini` یا `Google AI Studio` پیشنهاد می‌شود.** `ENTER` بزن.

اگر ارائه‌دهنده‌ای انتخاب کنی که کلید API لازم دارد، ویزارد کلید را ازت می‌خواهد. اگر [مرحله ۴ راهنمای نصب](INSTALL_HERMES_TERMUX.md#مرحله-۴--تنظیم-کلید-api) را انجام داده باشی، کلید از قبل در `~/.hermes/.env` هست.

---

## Step 3 — Select terminal backend

**حتماً باید `Local` باشد.** این تنظیم برای اندروید ضروری است.

</div>

```
Select terminal backend:
↑↓ navigate  ENTER/SPACE select  ESC cancel

→ (0) Local - run directly on this machine (default)
  (0) Docker - isolated container with configurable resources
  (0) Modal - serverless cloud sandbox
  (0) SSH - run on a remote machine
  (0) Daytona - persistent cloud development environment
  (●) Keep current (local)
```

<div dir="rtl">

| گزینه | توضیح |
|---|---|
| **Local** | دستورات مستقیم روی Termux اجرا می‌شوند. **گزینه‌ی درست برای اندروید همین است.** |
| **Docker** | کانتینر Docker (اندروید پشتیبانی نمی‌کند) |
| **Modal** | سرورلس ابری (نیاز به حساب Modal) |
| **SSH** | اجرا روی یک ماشین راه دور |
| **Daytona** | محیط توسعه‌ی ابری |
| **Keep current (local)** | حفظ همان تنظیم فعلی (اگر از قبل local است، همین کافی است) |

**← `Local` (یا `Keep current (local)`) را انتخاب کن و `ENTER` بزن.**

---

## Step 4 — Select platforms to configure

انتخاب پلتفرم‌های پیام‌رسان. این‌ها فقط برای ساخت ربات تلگرام/دیسکورد/واتساپ و مانند آن لازم‌اند. **برای اتصال اپ Hermes2 لازم نیستند.**

</div>

```
Select platforms to configure:
↑↓ navigate  SPACE toggle  ENTER confirm  ESC cancel

[ ] Mattermost    [ ] Discord       [ ] Slack
[ ] Signal        [ ] Email         [ ] Telegram
[ ] WeChat        [ ] Google Chat   [ ] WhatsApp
[ ] LINE          [ ] Matrix        [ ] Microsoft Teams
... (25 پلتفرم)
```

<div dir="rtl">

**← هیچ‌کدام را تیک نزن؛ فقط `ENTER` بزن تا رد شوی.** بعداً هر وقت خواستی می‌توانی اضافه‌شان کنی.

---

## Step 5 — Tools for CLI (تنظیمات پیشنهادی)

ابزارهایی که ایجنت اجازه‌ی استفاده از آن‌ها را دارد. با `↑↓` حرکت کن، با `SPACE` تیک بزن یا بردار، در پایان `ENTER` بزن.

</div>

```
Tools for 📱 CLI
↑↓ navigate  SPACE toggle  ENTER confirm  ESC cancel

→ [x] 🔍 Web Search & Scraping (web_search, web_extract)
  [ ] 🌐 Browser Automation (navigate, click, type, scroll)
  [ ] 💻 Terminal & Processes (terminal, process)
  [ ] 📁 File Operations (read, write, patch, search)
  ...
```

<div dir="rtl">

**این‌ها را فعال کن** (روی اندروید کار می‌کنند):

</div>

```
[x] 🔍 Web Search & Scraping
[x] 💻 Terminal & Processes
[x] 📁 File Operations
[x] ⚡ Code Execution
[x] 🔊 Text-to-Speech
[x] 📚 Skills
[x] 📝 Task Planning
[x] 🧠 Memory
[x] 🔍 Session Search
[x] ❓ Clarifying Questions
[x] 📤 Task Delegation
[x] 🕒 Cron Jobs
```

<div dir="rtl">

**این‌ها را فعال نکن** (اندروید پشتیبانی نمی‌کند یا تنظیمات جداگانه لازم دارند):

</div>

```
[ ] 🌐 Browser Automation    — روی اندروید کار نمی‌کند
[ ] 💻 Computer Use           — مخصوص دسکتاپ
[ ] 🎨 Image Generation       — نیاز به API جداگانه
[ ] 🎬 Video Generation       — نیاز به API جداگانه
[ ] 🐦 X (Twitter) Search     — نیاز به xAI OAuth
[ ] 🏠 Home Assistant         — نیاز به سرور Home Assistant
[ ] 🎵 Spotify                — نیاز به حساب Spotify
[ ] 💬 Yuanbao                — نیاز به تنظیم جداگانه
```

<div dir="rtl">

**← بعد از تیک‌زدن ابزارهای بالا، `ENTER` بزن.**

---

## Step 6 — Select Search Provider

انتخاب موتور جست‌وجوی وب:

</div>

```
Select Search Provider:
↑↓ navigate  ENTER/SPACE select  ESC cancel

  (0) Nous Subscription [subscription]
  (0) Firecrawl Self-Hosted [free - self-hosted]
  (0) Brave Search (Free) [free] - 2k queries/mo
→ (0) DuckDuckGo (ddgs) [free - no key - search only]
  (0) Exa [paid]
  (0) Firecrawl [paid]
  (0) Tavily [paid]
  (0) Skip - keep defaults / configure later
```

<div dir="rtl">

| ارائه‌دهنده | هزینه | نیاز به کلید | توضیح |
|---|---|---|---|
| **DuckDuckGo** | رایگان | ❌ | بدون کلید، فقط جست‌وجو. **بهترین گزینه برای شروع.** |
| **Brave Search** | رایگان | ✅ | ۲۰۰۰ پرس‌وجو در ماه |
| **Tavily** | پولی | ✅ | جست‌وجو + استخراج محتوا |
| **Skip** | — | — | بعداً تنظیم کن |

**← `DuckDuckGo` را انتخاب کن** — رایگان است، کلید نمی‌خواهد و همان لحظه کار می‌کند.

> [!NOTE]
> «جست‌وجوی وب» با «Browser Automation» فرق دارد: DuckDuckGo فقط نتایج جست‌وجو را برمی‌گرداند و روی اندروید کار می‌کند؛ Browser Automation یک مرورگر واقعی را کنترل می‌کند و روی اندروید کار **نمی‌کند**.

---

## Step 7 — تست اتصال

بعد از پایان ویزارد، سلامت تنظیمات و مدل را تست کن:

</div>

```bash
hermes doctor --fix
hermes doctor
```

```bash
hermes -q "سلام، فقط در یک جمله بگو با چه مدلی جواب می‌دهی."
```

<div dir="rtl">

اگر جواب گرفتی، هرمس آماده است — فقط مانده اپ اندروید را وصل کنی.

---

## خلاصه‌ی گزینه‌های پیشنهادی

| مرحله | گزینه‌ی پیشنهادی |
|---|---|
| ۱. Setup mode | **Full Setup** |
| ۲. Provider | **Gemini** یا **Google AI Studio** |
| ۳. Terminal backend | **Local** (حتماً) |
| ۴. Platforms | هیچ‌کدام — فقط **ENTER** |
| ۵. Tools | فهرست پیشنهادی بالا |
| ۶. Search Provider | **DuckDuckGo** (رایگان، بدون کلید) |

---

**← مرحله‌ی بعد: [اتصال اپ به Gateway](GATEWAY_SETUP.md)**

**→ بازگشت: [نصب هرمس](INSTALL_HERMES_TERMUX.md)** · [README اصلی](../README.fa.md) · مرجع کامل: [راهنمای فنی](RUNNING_ON_ANDROID_TERMUX.md)
</div>
