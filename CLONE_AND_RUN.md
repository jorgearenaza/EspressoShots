# 📥 CLONAR Y EJECUTAR EN ANDROID STUDIO PANDA 2025.3.1

## ✅ Pre-requisitos en Windows 11

Antes de clonar, asegúrate de tener instalado:

- ✅ **Git for Windows** (https://git-scm.com/download/win)
- ✅ **Android Studio Panda 2025.3.1** (https://developer.android.com/studio)
- ✅ **Android SDK 34** (descargado automáticamente en Android Studio)
- ✅ **OpenJDK 21** (incluido en Android Studio)
- ✅ **4GB+ RAM disponible** para Gradle

### Verificar instalación en PowerShell/CMD:
```powershell
# Verificar Git
git --version
# Resultado esperado: git version 2.x.x

# Verificar Java
java -version
# Resultado esperado: openjdk version "21.0.x" o similar
```

---

## 🚀 PASO 1: CLONAR EL REPOSITORIO

### Opción A: Desde PowerShell

```powershell
# Navegar a donde quieres el proyecto (ej: Documentos)
cd $PROFILE\..\Documents
# O:
cd C:\Users\TuNombre\Documents

# Clonar repositorio
git clone https://github.com/jorgearenaza/EspressoShots.git

# Entrar a carpeta
cd EspressoShots
```

### Opción B: Desde CMD

```cmd
# Ir a documentos o carpeta de proyectos
cd %USERPROFILE%\Documents

# Clonar
git clone https://github.com/jorgearenaza/EspressoShots.git

# Entrar
cd EspressoShots
```

### Opción C: Desde Git Bash

```bash
cd ~/Documents
git clone https://github.com/jorgearenaza/EspressoShots.git
cd EspressoShots
```

---

## ✅ PASO 2: VERIFICAR CLONACIÓN EXITOSA

```powershell
# Listar contenido
ls -la
# Debe mostrar:
#   .git/
#   .gitignore
#   app/
#   build.gradle.kts
#   gradle/
#   gradlew
#   local.properties (NO debe existir aún - se genera automáticamente)
#   settings.gradle.kts
#   + documentos (README.md, etc.)

# Verificar que gradlew existe y es ejecutable
Test-Path .\gradlew
# Debe devolver: True

# Ver estructura de app
ls -r app/src/main/kotlin/com/jorgearenaza/espressoshots/
# Debe mostrar directorios: data/, di/, ui/, util/
```

---

## 🎯 PASO 3: ABRIR EN ANDROID STUDIO PANDA

### Método A: Desde Android Studio (Recomendado)

1. **Abrir Android Studio Panda 2025.3.1**
2. **Welcome screen** → Click en **"Open"** (o File → Open)
3. **Navegar** a la carpeta clonada:
   ```
   C:\Users\TuNombre\Documents\EspressoShots
   ```
   (O donde la hayas clonado)
4. **Seleccionar carpeta raíz** y hacer click **"OK"**
5. **Esperar Gradle Sync** (2-3 minutos):
   - Ver barra inferior: "Gradle sync in progress..."
   - Cuando complete: "Gradle sync finished successfully ✓"

### Método B: Desde Terminal (Alternativa)

```powershell
# En la carpeta del proyecto
cd C:\Users\TuNombre\Documents\EspressoShots

# Abrir Android Studio con el proyecto
# (Necesitas Android Studio en PATH, o:)
start "Android Studio" "C:\Program Files\Android\Android Studio\bin\studio.exe" .

# Alternativa más simple: desde Android Studio menú File → Open
```

---

## 🔨 PASO 4: COMPILAR APK

Una vez Android Studio terminó Gradle Sync (status en barra inferior):

### Opción 1: Desde UI de Android Studio (MÁS FÁCIL)

1. **Build** → **Build Bundle(s)/APK(s)** → **Build APK(s)**
2. Esperar compilación (3-5 minutos primera vez)
3. Ver notificación verde: **"APK(s) generated successfully for module 'app'"**
4. APK generado en: 
   ```
   app\build\outputs\apk\debug\app-debug.apk
   ```

### Opción 2: Desde Terminal en el Proyecto

```powershell
# Dentro de la carpeta del proyecto
cd C:\Users\TuNombre\Documents\EspressoShots

# Compilar
.\gradlew.bat clean :app:assembleDebug

# O más simplemente:
.\gradlew clean build

# Resultado: Debe decir "BUILD SUCCESSFUL" al final
```

### Opción 3: Si PowerShell da permisos

```powershell
# Si ves error de ejecución:
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser

# Luego:
.\gradlew.bat clean :app:assembleDebug
```

---

## ✅ PASO 5: VERIFICAR COMPILACIÓN EXITOSA

### En Android Studio

- Panel inferior derecho: "BUILD SUCCESSFUL"
- Notificación verde: "APK(s) generated successfully for module 'app'"
- Ver → Problems → "Analysis" debe mostrar 0 errores críticos

### En Terminal

```powershell
# Verificar APK fue generado
Test-Path ".\app\build\outputs\apk\debug\app-debug.apk"
# Resultado: True

# Ver tamaño (debe ser 5-10 MB)
(Get-Item ".\app\build\outputs\apk\debug\app-debug.apk").Length / 1MB
# Resultado: ej., 7.5
```

---

## 📱 PASO 6: INSTALAR Y EJECUTAR EN DEVICE/EMULATOR

### Opción A: Desde Android Studio (MÁS RECOMENDADO)

1. **Conectar device USB** O **abrir Android Emulator**
   - Device debe estar en "Developer Mode"
   - Si es emulator: Tools → Device Manager → seleccionar y Play

2. **Run → Run 'app'** (Shift+F10)

3. **Seleccionar device** en "Select Deployment Target"

4. **OK** → Instala y ejecuta automáticamente (30-60 segundos)

### Opción B: Desde Terminal

```powershell
# Primero, verificar device conectado
adb devices
# Resultado esperado:
# List of attached devices
# emulator-5554 device (o tu device real)

# Instalar APK
.\gradlew.bat :app:installDebug

# O directamente con adb:
adb install -r "app\build\outputs\apk\debug\app-debug.apk"

# Lanzar app
adb shell am start -n "com.jorgearenaza.espressoshots/.MainActivity"
```

---

## ✅ VERIFICACIÓN EN DEVICE/EMULATOR

Una vez la app está abierta, debes ver:

### Pantalla Inicial
- ✅ Splash/Logo cargando (2-3 segundos)
- ✅ **Bottom Navigation** con 5 tabs:
  - Shots
  - Granos
  - Molinos
  - Perfiles
  - Opciones

### Elementos Visuales
- ✅ **FAB rojo** (#D32F2F) en esquina inferior derecha
- ✅ **Status bar rojo**
- ✅ **Tab "Shots"** seleccionado por defecto

### Navegación por Tabs
Tap en cada tab y verifica:

| Tab | Esperado |
|-----|----------|
| **Shots** | Mensaje "No hay shots" + FAB rojo |
| **Granos** | Mensaje "No hay granos" + FAB rojo |
| **Molinos** | Mensaje "No hay molinos" + FAB rojo |
| **Perfiles** | Mensaje "No hay perfiles" + FAB rojo |
| **Opciones** | Formulario con 3 campos |

### Settings Form (Tab "Opciones")
Debe mostrar:
- TextField: "Dosis por Defecto" = **18.0**
- TextField: "Ratio por Defecto" = **2.0**
- Checkbox: "Autocompletar Shots" = **ACTIVADO** ✓
- Botón: "Guardar" (rojo #D32F2F)

---

## 🐛 TROUBLESHOOTING

### Problema 1: "Gradle sync failed" al abrir Android Studio

**Solución:**
```
File → Invalidate Caches... → Invalidate and Restart
```
Espera a que reinicie Android Studio completamente.

---

### Problema 2: "Cannot resolve symbol 'androidx.room...'" durante compilación

**Solución:**
1. Verificar `build.gradle.kts` tiene:
   ```kotlin
   kapt("androidx.room:room-compiler:2.6.1")
   ```
2. File → Sync Now
3. Build → Rebuild Project

---

### Problema 3: "Hilt: @HiltAndroidApp not applied"

**Solución:**
1. Abrir `app/src/main/kotlin/com/jorgearenaza/espressoshots/EspressoShotsApp.kt`
2. Verificar que tiene:
   ```kotlin
   @HiltAndroidApp
   class EspressoShotsApp : Application() { ... }
   ```
3. Rebuild Project (Build → Rebuild Project)

---

### Problema 4: "Compilation timeout" - Gradle tarda mucho

**Solución:**
```powershell
# Aumentar memoria de Gradle (Windows)
$env:GRADLE_OPTS = "-Xmx4g"

# Luego compilar
.\gradlew.bat clean :app:assembleDebug --no-daemon
```

---

### Problema 5: "No Android SDK found" o "SDK 34 not installed"

**Solución:**
1. Android Studio → **Tools** → **SDK Manager**
2. Buscar **"SDK Platforms"** tab
3. Descargar **"Android API 34"** si no está
4. Luego: File → Sync Now

---

### Problema 6: Device/Emulator no se ve en "Select Deployment Target"

**Solución para Device Real:**
```powershell
# Habilitar USB Debugging en phone:
# Settings → Developer Options → USB Debugging = ON

# Ver si se detecta:
adb devices
# Si aparece con "offline": desconectar/reconectar USB

# Verificar permisos:
adb tcpip 5555
```

**Solución para Emulator:**
1. Tools → Device Manager
2. Seleccionar device (ej., Pixel 6)
3. Click en **Play** (triángulo verde)
4. Esperar a que emulator arranque (puede tomar 1-2 min)
5. Luego Run 'app' desde Android Studio

---

### Problema 7: "adb: command not found" en PowerShell

**Solución:**
```powershell
# Buscar adb en Android SDK
$adb = "C:\Users\TuNombre\AppData\Local\Android\Sdk\platform-tools\adb.exe"

# Usar ruta completa:
& $adb devices

# O agregar a PATH:
$env:Path += ";C:\Users\TuNombre\AppData\Local\Android\Sdk\platform-tools"
adb devices
```

---

### Problema 8: App crashes al abrir ("App keeps stopping")

**Solución:**
```powershell
# Ver logs
adb logcat | findstr "EspressoShots" | findstr "ERROR"

# O desde Android Studio:
# View → Tool Windows → Logcat
# Buscar "ERROR" o "EspressoShots"
```

Causas comunes:
- Hilt no inyectó correctamente → Rebuild Project
- Room no compiló DAOs → Clean + Rebuild
- Android version no soportada → Probar en emulator API 30+

---

## 📋 CHECKLIST FINAL

Después de que la app funciona en device:

- [x] ✅ Repositorio clonado
- [x] ✅ Gradle Sync exitoso
- [x] ✅ APK compilado (app-debug.apk existe)
- [x] ✅ App instalada sin errores
- [x] ✅ App abrió sin crashes
- [x] ✅ 5 tabs visibles y clickeables
- [x] ✅ FAB es rojo (#D32F2F)
- [x] ✅ Tab "Opciones" muestra defaults
- [x] ✅ Botón "Guardar" funciona (tap → snackbar)
- [x] ✅ Empty state messages aparecen

---

## 🎯 PRÓXIMOS PASOS (OPCIONAL)

Una vez compilado y funcionando:

### 1. Hacer 5 Commits Lógicos
Ver → [COMMIT_GUIDE.md](COMMIT_GUIDE.md)

```bash
git log
# Debe mostrar los 5 commits
```

### 2. Crear Pull Request
```bash
git push origin rebuild/clean-mvvm
# Luego abrir PR en GitHub
```

### 3. Fase 2 (Forms completos, Date Pickers)
Ver → [PROJECT_SUMMARY.md](PROJECT_SUMMARY.md) sección "Fase 2"

---

## 📞 RESUMEN RÁPIDO (3 Comandos)

```powershell
# 1. Clonar
git clone https://github.com/jorgearenaza/EspressoShots.git
cd EspressoShots

# 2. Abrir en Android Studio
# File → Open → Seleccionar carpeta
# Esperar Gradle Sync

# 3. Compilar y ejecutar
# Build → Build APK(s)
# Run → Run 'app' (Shift+F10)
```

**¡Listo! App funcionando con MVVM + Compose + Material3 + Room + DataStore + Hilt ✅**

---

## 📚 Documentos Relacionados

- **[START_HERE.md](START_HERE.md)** - Inicio rápido (3 pasos)
- **[BUILD_PANDA_2025.md](BUILD_PANDA_2025.md)** - Guía detallada compilación
- **[BUILD_STATUS.md](BUILD_STATUS.md)** - Checklist estado proyecto
- **[CHECKLIST.md](CHECKLIST.md)** - Testing manual detallado
- **[COMMIT_GUIDE.md](COMMIT_GUIDE.md)** - 5 commits para PR

---

**Versión**: 1.0.0  
**Compatible con**: Windows 11, macOS, Linux  
**Android Studio**: Panda 2025.3.1 +  
**Kotlin**: 1.9.20  
**Android API**: 24-34  
**Status**: ✅ LISTO PARA CLONAR Y EJECUTAR
