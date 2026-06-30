# Changelog

All notable changes to **Hermes2** are documented here.
The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
- **Three professional color themes** — Midnight (ChatGPT-inspired), Indigo Pro
  (Linear-inspired), and Carbon (Perplexity-inspired). The app now ships **6
  selectable themes** with full light & dark variants.
- **Bilingual README** — English (`README.md`) + Persian (`README.fa.md`) with a
  language switcher.
- **Signed release pipeline** — `release.yml` builds a signed APK and publishes a
  GitHub Release when a `v*` tag is pushed; signing credentials come from
  repository secrets.

### Fixed
- Orphaned streaming message could stay stuck with a spinner after a disconnect /
  new turn — assistant messages are now finalized correctly.
- `retryLastMessage` no longer duplicates the user turn in the server history.
- Removed dead code in `ChatViewModel`.

### Changed
- Markdown rendering relies on `compose-markdown`'s bundled image loader; the
  unstable custom image viewer remains out of the app.

## [0.1.0] — initial

### Added
- Native **Material 3** Android UI for **Hermes Agent** (Termux runtime).
- **Chat** with live streaming, reasoning view, tool-call cards, sub-agent cards,
  slash commands, long-press menu, and auto-scroll.
- **Sessions** drawer (search · sort · pin · rename · delete) with resume.
- **Runtime Setup** screen — detect, install, start/stop the gateway.
- **Tool approval** via Android notifications (Approve / Deny).
- **Config, Skills, Cron & Platforms** screens.
- **Bilingual onboarding** (English & Persian).
- Foreground service to keep the gateway alive in the background.

[Unreleased]: https://github.com/traveler3022/Hermes2/compare/v0.1.0...HEAD
[0.1.0]: https://github.com/traveler3022/Hermes2/releases/tag/v0.1.0
