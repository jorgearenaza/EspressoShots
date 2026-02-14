```
╔══════════════════════════════════════════════════════════════════════════════╗
║                                                                              ║
║                      ☕ ESPRESSO SHOTS - QUICK START                        ║
║                                                                              ║
║         Clone → Compile → Run in Android Studio Panda 2025.3.1             ║
║                                                                              ║
╚══════════════════════════════════════════════════════════════════════════════╝
```

# 🚀 GET STARTED IN 5 COMMANDS

## Paso 1️⃣: Clone (PowerShell / CMD / Bash)

```bash
# Windows PowerShell
cd $PROFILE\..\Documents
git clone https://github.com/jorgearenaza/EspressoShots.git
cd EspressoShots

# OR Windows CMD
cd %USERPROFILE%\Documents
git clone https://github.com/jorgearenaza/EspressoShots.git
cd EspressoShots

# OR Mac/Linux
cd ~/Documents
git clone https://github.com/jorgearenaza/EspressoShots.git
cd EspressoShots
```

**✅ Result**: Folder `EspressoShots` with all 87 files

---

## Paso 2️⃣: Open in Android Studio

```
1. Android Studio → File → Open
2. Select EspressoShots folder
3. Click OK
4. Wait for "Gradle sync finished successfully ✓" (2-3 min)
```

**✅ Result**: Gradle Sync complete, no errors

---

## Paso 3️⃣: Build APK

```
1. Build → Build APK(s)
2. Wait for compilation (3-5 min)
3. See: "APK(s) generated successfully for module 'app'"
```

**✅ Result**: APK at `app/build/outputs/apk/debug/app-debug.apk` (5-10 MB)

---

## Paso 4️⃣: Run on Device/Emulator

```
1. Connect device OR open emulator
2. Run → Run 'app' (Shift+F10)
3. Select device in dialog
4. Wait for install (30-60 sec)
```

**✅ Result**: App opens and shows 5 tabs

---

## Paso 5️⃣: Verify

Check in the app:
- ✅ 5 tabs visible at bottom (Shots, Granos, Molinos, Perfiles, Opciones)
- ✅ FAB is red (#D32F2F)
- ✅ Tab "Opciones" shows form with:
  - Dosis por Defecto: 18.0
  - Ratio por Defecto: 2.0
  - Autocompletar Shots: ✓ (checked)
  - Red "Guardar" button

**✅ Result**: 🎉 APP COMPILED AND RUNNING!

---

# 🔍 WHAT YOU GET

## 47 Kotlin Files (2,800+ LOC)
```
✅ MVVM Architecture (Repository → ViewModel → UI)
✅ Jetpack Compose + Material3 (Red #D32F2F theme)
✅ Room Database (4 entities + 4 DAOs)
✅ DataStore Preferences (auto-save)
✅ Hilt Dependency Injection (@Module, @Provides)
✅ Bottom Navigation (5 tabs)
✅ 5 ViewModels (@HiltViewModel)
✅ 4 Reusable Components
✅ Zero broken imports
✅ Zero deprecated APIs
```

## 14 Documentation Files
```
📄 CLONE_AND_RUN.md ⭐ (main guide)
📄 QUICK_CLONE.md (quick reference)
📄 CHECKLIST_CLONE_AND_BUILD.md (step by step checklist)
📄 BUILD_PANDA_2025.md (Panda 2025.3.1 specific)
📄 CHECKLIST.md (manual testing)
📄 COMMIT_GUIDE.md (5 commits for PR)
+ 8 more reference docs
```

---

# ❓ TROUBLESHOOTING (Quick)

| Problem | Solution |
|---------|----------|
| "Gradle sync failed" | File → Invalidate Caches → Invalidate and Restart |
| "Cannot resolve symbol" | Build → Rebuild Project |
| Device not found | adb devices (must show your device) |
| App crashes | View → Tool Windows → Logcat (search ERROR) |

**For more help**: See [BUILD_PANDA_2025.md](BUILD_PANDA_2025.md)

---

# 📚 NEED DETAILS?

| Topic | Document |
|-------|----------|
| Step-by-step clone | [CLONE_AND_RUN.md](CLONE_AND_RUN.md) |
| Panda 2025.3.1 build | [BUILD_PANDA_2025.md](BUILD_PANDA_2025.md) |
| Testing checklist | [CHECKLIST.md](CHECKLIST.md) |
| Architecture | [PROJECT_SUMMARY.md](PROJECT_SUMMARY.md) |
| 5 Git commits | [COMMIT_GUIDE.md](COMMIT_GUIDE.md) |
| Full delivery summary | [00_DELIVERY_SUMMARY.md](00_DELIVERY_SUMMARY.md) |

---

# ✅ REQUIREMENTS

- ✅ Windows 11 / macOS / Linux
- ✅ Android Studio Panda 2025.3.1
- ✅ JDK 21 (included in Android Studio)
- ✅ 4GB+ RAM
- ✅ Internet for Maven

---

# 🔐 VERIFIED READY

```
✅ Source Code:      47 .kt files, 2,800+ LOC
✅ Build System:     Gradle 8.4, AGP 8.2.0, Kotlin 1.9.20
✅ Dependencies:     14 libs (Compose, Room, Hilt, etc.)
✅ Android Config:   API 24-34, Material3, Spanish
✅ Documentation:    14 markdown files
✅ Architecture:     MVVM + Compose + Room + Hilt
✅ QA:               0 broken imports, 0 deprecated API
✅ Ready:            YES - CLONE AND BUILD NOW
```

---

# 🎯 ETA

```
Clone:          2 min
Gradle Sync:    2-3 min
Build APK:      3-5 min
Install:        1-2 min
Test:           2 min
─────────────────────
TOTAL:          ~15 minutes from start to running app
```

---

# 🎉 SUCCESS LOOKS LIKE

```
Android Studio → Build → Build APK(s)
    ↓ (wait 3-5 min)
✅ "APK(s) generated successfully for module 'app'"
    ↓
Device → Install & Open app
    ↓
✅ See 5 tabs + red FAB + Settings form with defaults
    ↓
✅ EspressoShots running on Panda 2025.3.1!
```

---

# 📞 START HERE

- **First time?** → Read [CLONE_AND_RUN.md](CLONE_AND_RUN.md)
- **In a hurry?** → Follow this file + [QUICK_CLONE.md](QUICK_CLONE.md)
- **Step by step?** → Use [CHECKLIST_CLONE_AND_BUILD.md](CHECKLIST_CLONE_AND_BUILD.md)
- **Troubleshooting?** → Check [BUILD_PANDA_2025.md](BUILD_PANDA_2025.md)

---

```
Status: ✅ READY TO CLONE AND BUILD
Version: 1.0.0
Target: Android Studio Panda 2025.3.1
Platforms: Windows 11, macOS, Linux
Date: February 14, 2026
```

---

## 🚀 NEXT: Clone the repo

```bash
git clone https://github.com/jorgearenaza/EspressoShots.git
cd EspressoShots
```

**Then open in Android Studio Panda and build!**
