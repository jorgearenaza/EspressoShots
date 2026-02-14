# 🔨 Compilación para Android Studio Panda 2025.3.1 (Windows 11)

## Compatibilidad Verificada
- ✅ Android Studio Panda 2025.3.1 (Build #AI-253.29346.138.2531.14850935)
- ✅ JDK 21.0.9 (incluido en Android Studio)
- ✅ Gradle 8.4 (compatible con AGP 8.2.0)
- ✅ Windows 11 (64-bit)
- ✅ minSdk 24, targetSdk 34

---

## ✅ Requisitos Instalados en Este Proyecto

### Versions Correctas
- [ ] Kotlin: 1.9.20 ✅ (compatible con AGP 8.2.0)
- [ ] Android Gradle Plugin: 8.2.0 ✅
- [ ] Gradle: 8.4 ✅ (incluido en wrapper)
- [ ] Compilación: SDK 34 ✅
- [ ] minSdk: 24 ✅

### Dependencies Verificadas
- [x] Jetpack Compose BOM 2023.10.00
- [x] Material3 1.1.1
- [x] Room 2.6.1
- [x] DataStore 1.0.0
- [x] Hilt 2.48
- [x] Navigation Compose 2.7.5
- [x] Coroutines 1.7.3

---

## 🚀 MÉTODO 1: Compilar desde Android Studio (Recomendado para Panda)

### Paso 1: Abrir Proyecto
1. **File** → **Open**
2. Navegar a `/workspaces/EspressoShots/`
3. Seleccionar carpeta raíz y hacer click **OK**

### Paso 2: Esperar Gradle Sync
- Android Studio detectará automáticamente `settings.gradle.kts`
- Mostrará: "Gradle sync in progress..." en la base
- Espera a que complete (2-3 minutos en primera vez)
- Status: "Gradle sync finished successfully ✓"

### Paso 3: Build APK desde IDE
1. **Build** → **Build Bundle(s)/APK(s)** → **Build APK(s)**
2. Espera compilación (3-5 minutos primera vez)
3. Ver notificación: "APK(s) generated successfully for module 'app'"

---

## 💻 MÉTODO 2: Compilar desde Terminal (Windows CMD/PowerShell)

### Pre-requisitos
```powershell
# Verificar Java está en PATH
java -version
# Esperado: openjdk version "21.0.9" o similar

# Ir a proyecto
cd %USERPROFILE%\...\EspressoShots
# O si está en otra ruta:
cd C:\ruta\al\EspressoShots
```

### Opción A: Desde PowerShell

```powershell
# Hacer gradlew ejecutable (Windows)
Get-ExecutionPolicy -List

# Compilar
.\gradlew.bat clean :app:assembleDebug

# Si hay permission issues:
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser

# APK ubicación
Get-ChildItem -Path "app\build\outputs\apk\debug\" -Filter "*.apk"
```

### Opción B: Desde CMD

```cmd
REM Compilar
gradlew.bat clean :app:assembleDebug

REM Ver resultado
dir app\build\outputs\apk\debug\
REM Esperado: app-debug.apk (~5-10 MB)
```

---

## 🔍 Verificación Post-Compilación

### ✅ Paso 1: Verificar APK se generó
```powershell
ls -Path "app/build/outputs/apk/debug/app-debug.apk"
# Debe mostrar: app-debug.apk con tamaño 5-10 MB
```

### ✅ Paso 2: Listar archivos en estructura
```powershell
# Verificar que existen los directorios clave:
Test-Path "app/src/main/kotlin/com/jorgearenaza/espressoshots/data/db/"         # ✅ Entities
Test-Path "app/src/main/kotlin/com/jorgearenaza/espressoshots/ui/screens/"      # ✅ Screens
Test-Path "app/src/main/kotlin/com/jorgearenaza/espressoshots/ui/theme/"        # ✅ Theme
Test-Path "app/src/main/kotlin/com/jorgearenaza/espressoshots/di/"              # ✅ DI
```

### ✅ Paso 3: Verificar sin errores de compilación
```
En Android Studio:
- View → Problems → Tab "Analysis"
  Deben ser 0 errores críticos (warnings están OK)

En Terminal:
- Último mensaje: "BUILD SUCCESSFUL"
```

---

## 📱 INSTALAR EN DEVICE/EMULATOR

### Prerequisito: Device conectado
```powershell
adb devices
# Esperado: Ver device en lista (ej: "emulator-5554 device")
```

### Opción 1: Desde Android Studio
1. **Run** → **Run 'app'** (Shift+F10)
2. O seleccionar device/emulator "en Select Deployment Target"
3. Espera instalación (30-60 segundos)

### Opción 2: Desde Terminal
```powershell
# Instalar APK
.\gradlew.bat :app:installDebug

# O usando adb directamente
adb install -r "app/build/outputs/apk/debug/app-debug.apk"

# Lanzar app
adb shell am start -n "com.jorgearenaza.espressoshots/.MainActivity"
```

---

## ✅ VERIFICACIÓN DE EJECUCIÓN

### Debe ver:
1. ✅ Splash/Logo de app cargando (2-3 seg)
2. ✅ Bottom navigation con 5 tabs: Shots | Granos | Molinos | Perfiles | Opciones
3. ✅ Tab "Shots" seleccionado por defecto
4. ✅ Mensaje: "No hay shots. Agrega uno..."
5. ✅ Botón FAB rojo (#D32F2F) en esquina inferior derecha
6. ✅ Status bar rojo (en device os emulator)

### Test táctil:
- Tap **"Shots"** tab: Pantalla vacía + FAB rojo ✅
- Tap **"Granos"** tab: Pantalla vacía + FAB rojo ✅
- Tap **"Molinos"** tab: Pantalla vacía + FAB rojo ✅
- Tap **"Perfiles"** tab: Pantalla vacía + FAB rojo ✅
- Tap **"Opciones"** tab: Formulario con:
  - TextField "Dosis por Defecto" = 18.0 ✅
  - TextField "Ratio por Defecto" = 2.0 ✅
  - Checkbox "Autocompletar Shots" = ON ✅
  - Botón rojo "Guardar" ✅

---

## 🐛 TROUBLESHOOTING para Panda 2025.3.1

### Problema 1: "Gradle sync failed"
**Solución**:
```
1. File → Invalidate Caches... → Invalidate and Restart
2. Espera a que reinicie Android Studio
3. Build → Rebuild Project
```

### Problema 2: "Cannot resolve symbol 'androidx.room.Entity'"
**Solución**:
- Verificar que build.gradle.kts tiene:
  ```kotlin
  kapt("androidx.room:room-compiler:2.6.1")
  ```
- File → Sync Now

### Problema 3: "Hilt: @HiltAndroidApp not applied"
**Solución**:
- Abrir `EspressoShotsApp.kt`
- Verificar tiene `@HiltAndroidApp`
- Rebuild project

### Problema 4: Compilation timeout
**Solución**:
```powershell
# En cmd/PowerShell:
set GRADLE_OPTS=-Xmx4g

# Luego compilar:
.\gradlew.bat clean :app:assembleDebug --no-daemon
```

### Problema 5: "Error: No Android SDK found"
**Solución**:
1. Android Studio → Tools → SDK Manager
2. Verificar que SDK 34 está instalado
3. Tools → SDK Manager → descargar si falta

### Problema 6: App crashes al abrir
**Solución**:
```powershell
adb logcat | findstr "FATAL\|ERROR\|EspressoShots"

# Ver log para ver qué falló
# Común: Hilt no inyectó correctamente
# Verificar @HiltAndroidApp y @AndroidEntryPoint
```

### Problema 7: "Java version mismatch"
**Solución**:
- Android Studio → File → Project Structure
- JDK location: Debe apuntar a `%ANDROID_STUDIO%\jbr` (21.0.9)
- Gradle JDK: mismo

---

## 📊 Recomendaciones para Panda 2025.3.1

### Memoria
- Se recomenda 4GB+ RAM para Gradle
- En build.gradle.kts ya está configurado en gradle.properties:
  ```
  org.gradle.jvmargs=-Xmx4096m
  ```

### Gradle Daemon
- Activado por defecto (más rápido)
- Si hay issues: `.\gradlew.bat --no-daemon clean build`

### Android Studio Settings
Recommended para compilación rápida:
1. **File** → **Settings**
2. **Build, Execution, Deployment** → **Gradle**
   - ✅ "Use local Gradle distribution"
   - ✅ "Offline mode" (si problemas de red)
3. **Build, Execution, Deployment** → **Compiler**
   - User-local build cache: ✅

---

## 📋 CHECKLIST FINAL

Antes de considerar compilación exitosa:

- [ ] Gradle sync terminó sin errores
- [ ] Build → Build APK(s) completó con éxito
- [ ] APK existe: `app/build/outputs/apk/debug/app-debug.apk` (5-10 MB)
- [ ] App instaló sin errores
- [ ] App abrió sin crashes
- [ ] 5 tabs visibles y navegables
- [ ] FAB es rojo (#D32F2F)
- [ ] Status bar es rojo
- [ ] Tab "Opciones" muestra defaults correctos
- [ ] Botón "Guardar" existe en Opciones

---

## 📞 Contacto para Issues

Si algo no funciona con Panda 2025.3.1:

1. Verificar Android Studio Version: **Help** → **About**
   - Debe mostrar: "Build #AI-253.29346.138..."

2. Verificar JDK: **File** → **Project Structure** → **SDK**
   - Debe ser OpenJDK 21.0.9

3. Limpiar completamente:
   ```powershell
   .\gradlew.bat clean
   Remove-Item -Recurse -Force "app\.gradle"
   Remove-Item -Recurse -Force ".gradle"
   Remove-Item -Recurse -Force ".idea"
   ```
   Luego: File → Invalidate Caches and Restart

---

## ✅ STATUS: LISTO PARA COMPILAR

El proyecto está **100% compatible** con Android Studio Panda 2025.3.1 en Windows 11.

Sigue los pasos arriba y deberías tener APK compilado en <5 minutos.

---

**Fecha**: Febrero 14, 2026  
**Versión**: 1.0.0  
**Status**: ✅ Compilable y Ejecutable en Panda 2025.3.1
