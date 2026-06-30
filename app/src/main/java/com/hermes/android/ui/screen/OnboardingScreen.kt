package com.hermes.android.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hermes.android.ui.i18n.t

@Composable
fun OnboardingScreen(
    onComplete: () -> Unit,
) {
    var page by remember { mutableIntStateOf(0) }
    val totalPages = 5

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            when (page) {
                0 -> WelcomePage()
                1 -> TermuxSetupPage()
                2 -> HermesInstallPage()
                3 -> GatewaySyncPage()
                4 -> DonePage()
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (page > 0) {
                    OutlinedButton(onClick = { page-- }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(t("Back", "قبلی"))
                    }
                } else {
                    TextButton(onClick = onComplete) {
                        Text(t("Skip", "رد شدن"))
                    }
                }

                Text(
                    text = "${page + 1} / $totalPages",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )

                if (page < totalPages - 1) {
                    Button(onClick = { page++ }) {
                        Text(t("Next", "بعدی"))
                        Spacer(Modifier.width(4.dp))
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                        )
                    }
                } else {
                    Button(onClick = onComplete) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(t("Get Started", "شروع کنید"))
                    }
                }
            }
        }
    }
}

@Composable
private fun WelcomePage() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Icon(
            Icons.Default.RocketLaunch,
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.primary,
        )

        Text(
            text = t("Welcome to Hermes", "به هرمس خوش آمدید"),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
        )

        Text(
            text = t(
                "Hermes is your AI-powered assistant running locally on your Android device via Termux. Follow these steps to get everything set up.",
                "هرمس دستیار هوش مصنوعی شماست که به صورت محلی روی دستگاه اندروید شما از طریق ترموکس اجرا می‌شود. این مراحل را دنبال کنید تا همه چیز آماده شود."
            ),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
            ),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = t("Setup Overview", "مرور کلی راه‌اندازی"),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
                SetupStep("1", t("Install Termux", "نصب ترموکس"), t("from F-Droid with local connection type", "از F-Droid با نوع اتصال لوکال"))
                SetupStep("2", t("Install Hermes", "نصب هرمس"), t("run installer command in Termux", "اجرای دستور نصب در ترموکس"))
                SetupStep("3", t("Sync Gateway", "همگام‌سازی گیت‌وی"), t("one-time sync on first launch", "همگام‌سازی یک‌بار در اولین راه‌اندازی"))
            }
        }
    }
}

@Composable
private fun SetupStep(number: String, title: String, desc: String) {
    Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(12.dp),
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = number,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold,
            )
        }
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
            Text(
                text = desc,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f),
            )
        }
    }
}

@Composable
private fun TermuxSetupPage() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Icon(
            Icons.Default.Download,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.primary,
        )

        Text(
            text = t("Step 1: Install Termux", "مرحله ۱: نصب ترموکس"),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
        )

        Text(
            text = t(
                "Install Termux from F-Droid (NOT Google Play — that version is outdated and won't work).",
                "ترموکس را از F-Droid نصب کنید (نه گوگل پلی — آن نسخه قدیمی است و کار نمی‌کند)."
            ),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = t("Install steps:", "مراحل نصب:"),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = t(
                        "1. Download F-Droid from f-droid.org\n2. Open F-Droid → search \"Termux\"\n3. Install Termux\n4. Open Termux once to initialize",
                        "۱. F-Droid را از f-droid.org دانلود کنید\n۲. F-Droid را باز کنید و «Termux» را جستجو کنید\n۳. ترموکس را نصب کنید\n۴. ترموکس را یک بار باز کنید تا راه‌اندازی شود"
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        // Critical note about connection type
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
            ),
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.Top,
            ) {
                Icon(
                    Icons.Default.Wifi,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onErrorContainer,
                )
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = t("Important: Connection Type", "مهم: نوع اتصال"),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onErrorContainer,
                    )
                    Text(
                        text = t(
                            "During Termux setup, when asked about connection type, select \"Local\" (localhost). This allows Hermes to communicate with the app over the local loopback interface.",
                            "هنگام راه‌اندازی ترموکس، وقتی از نوع اتصال پرسیده می‌شود، گزینه‌ی «لوکال» (localhost) را انتخاب کنید. این کار به هرمس اجازه می‌دهد از طریق رابط لوپ‌بک محلی با اپ ارتباط برقرار کند."
                        ),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onErrorContainer,
                    )
                }
            }
        }
    }
}

@Composable
private fun HermesInstallPage() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Icon(
            Icons.Default.Terminal,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.primary,
        )

        Text(
            text = t("Step 2: Install Hermes", "مرحله ۲: نصب هرمس"),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
        )

        Text(
            text = t(
                "Open Termux and run the following command to install Hermes:",
                "ترموکس را باز کنید و دستور زیر را برای نصب هرمس اجرا کنید:"
            ),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
        ) {
            Text(
                text = "curl -fsSL https://hermes.run/install.sh | bash",
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.padding(16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        Text(
            text = t(
                "This installs all required dependencies and configures the Hermes agent. It may take a few minutes.",
                "این دستور تمام وابستگی‌های مورد نیاز را نصب و هرمس را پیکربندی می‌کند. ممکن است چند دقیقه طول بکشد."
            ),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = t("After installation:", "بعد از نصب:"),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = t(
                        "Configure your AI provider in Hermes:\n  hermes config set model.provider <your-provider>",
                        "تنظیم مدل هوش مصنوعی در هرمس:\n  hermes config set model.provider <ارائه‌دهنده>"
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
private fun GatewaySyncPage() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Icon(
            Icons.Default.Sync,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.primary,
        )

        Text(
            text = t("Step 3: First-Launch Sync", "مرحله ۳: همگام‌سازی اولین اجرا"),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
        )

        Text(
            text = t(
                "On the first launch only, the gateway needs a one-time sync so the app can connect. After this, it reconnects automatically every time. Follow these steps in order:",
                "فقط در اولین اجرا، گیت‌وی به یک همگام‌سازی یک‌بار نیاز دارد تا اپ بتواند متصل شود. بعد از آن، هر بار خودکار وصل می‌شود. این مراحل را به‌ترتیب انجام دهید:"
            ),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = t("Sync steps:", "مراحل همگام‌سازی:"),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                )

                SyncStep(
                    "۱ / 1",
                    t(
                        "After setup finishes, Termux opens a fresh prompt. Run:",
                        "بعد از پایان نصب، ترموکس یک خط‌فرمان تازه باز می‌کند. اجرا کنید:"
                    ),
                    code = "hermes dashboard --stop",
                )

                SyncStep(
                    "۲ / 2",
                    t(
                        "Leave Termux, then Force stop it (Settings → Apps → Termux → Force Stop) to free the gateway port.",
                        "از ترموکس خارج شوید، سپس آن را Force stop کنید (تنظیمات → برنامه‌ها → Termux → توقف اجباری) تا پورت گیت‌وی آزاد شود."
                    ),
                )

                SyncStep(
                    "۳ / 3",
                    t(
                        "Back in this app, tap \"Open runtime host app\" — Termux opens.",
                        "در همین اپ، روی «Open runtime host app» بزنید — ترموکس باز می‌شود."
                    ),
                )

                SyncStep(
                    "۴ / 4",
                    t(
                        "Return to the app and tap \"Start Agent Gateway\".",
                        "به اپ برگردید و روی «Start Agent Gateway» بزنید."
                    ),
                )

                SyncStep(
                    "۵ / 5",
                    t(
                        "Wait ~30 seconds — the status turns to ✓ Connected.",
                        "حدود ۳۰ ثانیه صبر کنید — وضعیت به ✓ Connected تغییر می‌کند."
                    ),
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            ),
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.Top,
            ) {
                Icon(
                    Icons.Default.Warning,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.onTertiaryContainer,
                )
                Text(
                    text = t(
                        "Only needed the first time. If it doesn't connect after ~30s, repeat steps 3–5.",
                        "فقط بار اول لازم است. اگر بعد از ~۳۰ ثانیه وصل نشد، مراحل ۳ تا ۵ را تکرار کنید."
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                )
            }
        }
    }
}

@Composable
private fun SyncStep(number: String, text: String, code: String? = null) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Text(
            text = number,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.width(36.dp),
        )
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            if (code != null) {
                Text(
                    text = code,
                    style = MaterialTheme.typography.bodySmall,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

@Composable
private fun DonePage() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Icon(
            Icons.Default.Check,
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.primary,
        )

        Text(
            text = t("You're Ready!", "آماده‌اید!"),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
        )

        Text(
            text = t(
                "Setup is complete. You can now start chatting with Hermes. If you haven't done the gateway sync yet, go to Runtime Setup from the app menu.",
                "راه‌اندازی کامل شد. اکنون می‌توانید با هرمس گفتگو کنید. اگر هنوز همگام‌سازی گیت‌وی را انجام نداده‌اید، از منوی اپ به بخش Runtime Setup بروید."
            ),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
            ),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = t("Quick tips:", "نکات سریع:"),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
                Text(
                    text = t(
                        "• Configure your AI backend in Settings → Config\n• Runtime Setup shows gateway connection status\n• The app reconnects automatically if Termux restarts",
                        "• مدل هوش مصنوعی را در تنظیمات → پیکربندی تنظیم کنید\n• Runtime Setup وضعیت اتصال گیت‌وی را نشان می‌دهد\n• اپ در صورت راه‌اندازی مجدد ترموکس به صورت خودکار متصل می‌شود"
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
        }
    }
}
