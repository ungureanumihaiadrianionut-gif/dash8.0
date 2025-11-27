
# Renault OBD Dash - Full Scaffold (ready for GitHub)

This project contains a full scaffold for the Renault OBD Dash app you requested.
It includes:
- Kotlin + Jetpack Compose UI scaffold
- OBD/ELM327 Bluetooth connector and basic PID polling (RPM, coolant, MAP)
- DTC manager
- Auto-start foreground service
- BMW-style UI composable placeholders
- GitHub Actions workflow (debug build)

**To use:**
1. Replace placeholder images in `app/src/main/res/drawable/`.
2. Commit and push this repository to your GitHub (branch `main`) as the repository root (do NOT nest inside another folder).
3. The Actions workflow `.github/workflows/android-ci.yml` builds `assembleDebug` and uploads `app-debug.apk` as artifact.
4. For signed release builds, follow the keystore instructions earlier in the conversation to add secrets and a release workflow.

**Notes & limitations:**
- Manufacturer proprietary PIDs are not included. Use the raw CAN logger to capture frames for later analysis.
- This scaffold is intended for testing and development. Do not perform ECU writes beyond DTC clear without OEM tools.
