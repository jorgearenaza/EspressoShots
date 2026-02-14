# ⚡ Quick Start - EspressoShots

## 5 Minutos para Estar Listo

### 1️⃣ Preparar Gradlew (30 seg)
```bash
cd /workspaces/EspressoShots
chmod +x ./gradlew
```

### 2️⃣ Compilar APK (3-5 min)
```bash
./gradlew clean :app:assembleDebug
```
**Resultado**: `app/build/outputs/apk/debug/app-debug.apk`

### 3️⃣ Instalar en Device/Emulator (1 min)
```bash
./gradlew :app:installDebug
```

### 4️⃣ Lanzar Aplicación (10 seg)
```bash
adb shell am start -n com.jorgearenaza.espressoshots/.MainActivity
```

---

## ¿Qué Esperar?

✅ App abre con Bottom Nav (5 tabs): **Shots, Granos, Molinos, Perfiles, Opciones**

✅ Cada tab muestra una **lista vacía** con mensaje "No hay X. Agrega uno..."

✅ **Botones rojos** (FAB) en cada tab para agregar elementos

✅ **Tab Opciones** muestra valores por defecto:
- Dosis: 18.0g
- Ratio: 2.0  
- Checkbox "Autocompletar Shots"
- Botón rojo "Guardar"

✅ **Theme**: Rojo espresso (#D32F2F) en FABs y status bar

✅ **Sin errores**, sin crashes, sin imports rotos

---

## Si Algo Falla

### Error: `gradlew not found`
```bash
ls -la | grep gradlew  # Verificar existencia
chmod +x ./gradlew      # Hacer ejecutable
```

### Error: `Gradle sync failed`
```bash
./gradlew --refresh-dependencies
./gradlew clean
```

### Error: `Cannot find Room compiler`
✅ Ya está configurado con kapt en build.gradle.kts

### Error: `Hilt @HiltAndroidApp not applied`
✅ Ya está en EspressoShotsApp.kt

---

## Archivos Clave

| Archivo | Propósito |
|---------|-----------|
| `app/build.gradle.kts` | Deps + compileSdk |
| `EspressoShotsApp.kt` | @HiltAndroidApp entry |
| `MainActivity.kt` | Compose setContent |
| `ui/navigation/EspressoShotsNavHost.kt` | Bottom nav + 5 tabs |
| `data/db/AppDatabase.kt` | Room setup |
| `di/DataModule.kt` | Hilt @Module |
| `ui/screens/*.kt` | 5 pantallas |
| `ui/viewmodel/*.kt` | 5 ViewModels |

---

## Estructura de Carpetas Esencial

```
EspressoShots/
├── app/src/main/
│   ├── kotlin/.../espressoshots/
│   │   ├── data/db/       ← Room entities + DAOs
│   │   ├── data/repository/ ← CRUD wrappers
│   │   ├── di/            ← Hilt @Module
│   │   └── ui/            ← Compose screens + components
│   └── res/               ← strings.xml, colors.xml, icons
├── build.gradle.kts       ← Root gradle
├── app/build.gradle.kts   ← App gradle (todas las deps)
└── gradlew               ← Gradle wrapper executable
```

---

## Verificaciones Rápidas

### ✅ Compilación
```bash
./gradlew clean assembleDebug --info
```
Espera mensaje: `BUILD SUCCESSFUL`

### ✅ Size del APK
```bash
ls -lh app/build/outputs/apk/debug/app-debug.apk
```
Espera: ~5-10 MB

### ✅ Instalaci ó n
```bash
adb devices  # Verifica que haya device
./gradlew installDebug
```
Espera: `BUILD SUCCESSFUL` + `Installed on device`

### ✅ App Runs
```bash
adb shell am start -n com.jorgearenaza.espressoshots/.MainActivity
adb logcat | grep "EspressoShots"
```

---

## Testing Básico (Sin Código)

### Tap Shots
- ✅ Pantalla muestra "No hay shots. Agrega uno..."
- ✅ Botón rojo (FAB) visible en esquina inferior derecha
- ✅ Navigation bar con 5 tabs en bottom

### Tap Granos
- ✅ Mismo layout: lista vacía + FAB rojo
- ✅ Mensaje "No hay granos. Agrega uno..."

### Tap Molinos
- ✅ Ídem

### Tap Perfiles
- ✅ Ídem

### Tap Opciones
- ✅ TextField: "Dosis por Defecto" = 18.0
- ✅ TextField: "Ratio por Defecto" = 2.0
- ✅ Checkbox: "Autocompletar Shots" = ON
- ✅ Botón rojo "Guardar"

### Colors
- ✅ Status bar = rojo (#D32F2F)
- ✅ FABs = rojo
- ✅ "Guardar" button = rojo
- ✅ Textos = negro/gris legible

---

## Commits (Opcional pero Recomendado)

Si quieres hacer commits:

```bash
git status  # Ver cambios
git log --oneline  # Ver historia

# Commit 1
git add build.gradle.kts settings.gradle.kts app/build.gradle.kts *.properties
git commit -m "bootstrap: gradle config + dependencies"

# Commit 2
git add app/src/main/kotlin/com/jorgearenaza/espressoshots/data/
git commit -m "data: Room entities + DAOs + repositories"

# Commit 3
git add app/src/main/kotlin/com/jorgearenaza/espressoshots/di/
git add app/src/main/kotlin/com/jorgearenaza/espressoshots/ui/theme/
git add app/src/main/kotlin/com/jorgearenaza/espressoshots/ui/navigation/
git commit -m "di+theme+nav: Hilt setup, Material3, bottom navigation"

# Commit 4
git add app/src/main/kotlin/com/jorgearenaza/espressoshots/ui/screens/
git add app/src/main/kotlin/com/jorgearenaza/espressoshots/ui/components/
git add app/src/main/kotlin/com/jorgearenaza/espressoshots/ui/viewmodel/
git commit -m "ui: Screens + components + viewmodels"

# Commit 5
git add *.md  # README.md, etc
git commit -m "docs: README, guides, checklists"

# Verificar
git log --oneline  # Debe mostrar 5 commits nuevos
```

---

## PR (Pull Request)

```bash
git push origin rebuild/clean-mvvm
# Abrir PR en GitHub con:
# Title: "feat: Clean rebuild MVVM + Compose + Room"
# Description: (ver COMMIT_GUIDE.md)
```

---

## Resumen

| Paso | Tiempo | Comando |
|------|--------|---------|
| 1. Setup | 30s | `chmod +x ./gradlew` |
| 2. Build | 3-5min | `./gradlew clean assembleDebug` |
| 3. Install | 1min | `./gradlew installDebug` |
| 4. Launch | 10s | `adb shell am start ...` |
| 5. Test | 5min | Tap tabs, verify colors |
| **TOTAL** | **~10 min** | ✅ |

---

## Archivos Importantes para Consultar

📖 **Para entender la arquitectura**:
- `RESUMEN_EJECUTIVO.md` ← Estructura completa
- `PROJECT_SUMMARY.md` ← Breakdown detallado

📋 **Para testing**:
- `CHECKLIST.md` ← Test plan completo

🔧 **Para commits/PR**:
- `COMMIT_GUIDE.md` ← Instrucciones paso a paso

📚 **Para features**:
- `README.md` ← Features + cómo correr

---

## ¿Problemas?

### 1. Gradle no compila
```bash
./gradlew clean build --refresh-dependencies
```

### 2. Hilt errors
Verificar:
- ✅ `EspressoShotsApp.kt` tiene `@HiltAndroidApp`
- ✅ `MainActivity.kt` tiene `@AndroidEntryPoint`
- ✅ `DataModule.kt` existe y tiene `@Module`

### 3. Layout looks weird
- Verificar portrait mode en emulator
- Rotate screen (rotate 90)

### 4. No compila APK
```bash
./gradlew clean :app:assem bleDebug --debug  # Ver logs
```

---

## ¡Listo! 🎉

Ahora:
- ✅ App compila sin errores
- ✅ APK funciona
- ✅ 5 tabs funcionan
- ✅ Colors son rojos
- ✅ Listas vacías con CTAs

**Siguiente**: Agregar formularios para Add/Edit (fase 2)

---

**Tiempo total**: ~10 min desde inicio hasta app en device.

¡Disfruta! ☕📱
