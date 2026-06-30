# App store catalog (fastlane metadata)

This folder is the **app distribution catalog** in the standard
[fastlane](https://docs.fastlane.tools/actions/deliver/#available-metadata-folders)
layout — the same structure used by **F-Droid**, **IzzyOnDroid**, and the
Google Play "Triple-T" plugin. Listing platforms read it automatically.

## Structure

```
fastlane/metadata/android/
├── en-US/                      English listing
│   ├── title.txt               app name (≤ 30 chars)
│   ├── short_description.txt    one-liner (≤ 80 chars)
│   ├── full_description.txt     long description
│   ├── changelogs/
│   │   └── 1.txt                notes for versionCode 1 (0.1.0)
│   └── images/
│       ├── icon.png             512×512
│       ├── featureGraphic.png   1024×500
│       └── phoneScreenshots/    screenshot1.png, screenshot2.png, …
└── fa/                         Persian listing (same files)
```

## Text — done ✅

`title`, `short_description`, `full_description`, and the `1.txt` changelog
are written for both **en-US** and **fa**.

> When you bump `versionCode` in `app/build.gradle.kts`, add a matching
> `changelogs/<versionCode>.txt` in each locale.

## Images — you add these 📷

Image files are binary, so they aren't generated here. Drop them in with the
exact names and sizes above:

| File | Size | Notes |
|------|------|-------|
| `icon.png` | 512×512 PNG | the app icon |
| `featureGraphic.png` | 1024×500 PNG | banner shown at the top of the listing |
| `phoneScreenshots/*.png` | any phone size | 2–8 screenshots of the app |

> 💡 Easiest way to capture screenshots: run the app on your phone and take
> normal Android screenshots, then drop them into `phoneScreenshots/`.
> Use **emulator/clean** screenshots — not ones with personal data.
