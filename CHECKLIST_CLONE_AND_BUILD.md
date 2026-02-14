# 📋 CHECKLIST: Clonar y Compilar EspressoShots en Panda 2025.3.1

Use este checklist mientras sigue los pasos en [CLONE_AND_RUN.md](CLONE_AND_RUN.md)

---

## ANTES DE EMPEZAR
- [ ] Android Studio Panda 2025.3.1 instalado
- [ ] Git instalado (`git --version` funciona)
- [ ] OpenJDK 21 en PATH ([verificar](BUILD_PANDA_2025.md))
- [ ] 4GB+ RAM disponible
- [ ] Conexión internet (para Maven)

---

## PASO 1: CLONAR (2 minutos)

- [ ] Abrir PowerShell/CMD/Bash
- [ ] Navegar a carpeta destino: `cd %USERPROFILE%\Documents` (Windows) o `cd ~/Documents` (Mac/Linux)
- [ ] Clonar: `git clone https://github.com/jorgearenaza/EspressoShots.git`
- [ ] Entrar: `cd EspressoShots`
- [ ] Verificar estructura: `ls` o `dir` debe mostrar:
  - [ ] `.git/`
  - [ ] `app/`
  - [ ] `build.gradle.kts`
  - [ ] `settings.gradle.kts`
  - [ ] `gradlew`
  - [ ] `CLONE_AND_RUN.md`
  - [ ] `README.md`

---

## PASO 2: ABRIR EN ANDROID STUDIO (3 minutos)

- [ ] Abrir Android Studio Panda 2025.3.1
- [ ] **File** → **Open**
- [ ] Navegar a carpeta `EspressoShots` (donde clonaste)
- [ ] Seleccionar carpeta raíz
- [ ] Click **OK**
- [ ] Ver barra inferior: "Gradle sync in progress..."
- [ ] ⏳ Esperar 2-3 minutos
- [ ] Ver barra inferior: "Gradle sync finished successfully ✓"

**Si ves error "Gradle sync failed":**
- [ ] File → Invalidate Caches... → **Invalidate and Restart**
- [ ] Esperar reinicio
- [ ] Gradle Sync debería completarse ahora

---

## PASO 3: COMPILAR APK (5 minutos)

### Opción A: Desde Android Studio UI (Recomendado)
- [ ] Click en menú **Build**
- [ ] Seleccionar **Build Bundle(s)/APK(s)** → **Build APK(s)**
- [ ] ⏳ Esperar 3-5 minutos (primera vez es más lenta)
- [ ] Ver notificación verde: **"APK(s) generated successfully for module 'app'"**

### Opción B: Desde Terminal
- [ ] En terminal (dentro de carpeta EspressoShots)
- [ ] Windows CMD:
  ```
  gradlew.bat clean :app:assembleDebug
  ```
- [ ] Windows PowerShell:
  ```
  .\gradlew.bat clean :app:assembleDebug
  ```
- [ ] Mac/Linux:
  ```
  ./gradlew clean :app:assembleDebug
  ```
- [ ] ⏳ Esperar compilación
- [ ] Último mensaje debe ser: **"BUILD SUCCESSFUL"**

**Si ves error de compilación:**
- [ ] Ver [BUILD_PANDA_2025.md](BUILD_PANDA_2025.md) sección "🐛 TROUBLESHOOTING"
- [ ] Ejecutar: `File → Rebuild Project` en Android Studio

---

## PASO 4: VERIFICAR APK (1 minuto)

- [ ] Abrir File Explorer / Finder / Terminal
- [ ] Navegar a: `EspressoShots\app\build\outputs\apk\debug\`
- [ ] Verificar que existe: **`app-debug.apk`**
- [ ] Tamaño debe ser: **5-10 MB**

---

## PASO 5: INSTALAR EN DEVICE/EMULATOR (2 minutos)

### Opción A: Usando Android Studio (Más Fácil)

- [ ] Conectar Android device USB O abrir Emulator
  - [ ] **Device**: Settings → Developer Options → USB Debugging = ON
  - [ ] **Emulator**: Tools → Device Manager → seleccionar → Play

- [ ] Android Studio → **Run** → **Run 'app'** (Shift+F10)
  
- [ ] Ver diálogo: "Select Deployment Target"
  
- [ ] Seleccionar tu device/emulator
  
- [ ] Click **OK**
  
- [ ] ⏳ Instaling... (30-60 segundos)
  
- [ ] App se abre automáticamente

### Opción B: Usando Terminal (adb)

- [ ] Verificar device conectado:
  ```
  adb devices
  ```
  - [ ] Debe mostrar tu device/emulator en la lista

- [ ] Instalar APK:
  ```
  adb install -r app\build\outputs\apk\debug\app-debug.apk
  ```
  - [ ] Debe mostrar: "Success"

- [ ] Lanzar app:
  ```
  adb shell am start -n "com.jorgearenaza.espressoshots/.MainActivity"
  ```

---

## PASO 6: VERIFICAR EJECUCIÓN (2 minutos)

En el device/emulator, verifica:

### Pantalla Inicial
- [ ] Splash/Logo cargando (2-3 seg)
- [ ] Después: App abierta sin crashes

### UI Verification
- [ ] ✅ **Bottom Navigation** visible con 5 tabs:
  - [ ] **Shots**
  - [ ] **Granos**
  - [ ] **Molinos**
  - [ ] **Perfiles**
  - [ ] **Opciones**

- [ ] ✅ **Tab "Shots"** está seleccionado (por defecto)

- [ ] ✅ **FAB rojo** (#D32F2F) en esquina inferior derecha

- [ ] ✅ **Status bar es rojo**

### Navega por Cada Tab

Haz tap en cada tab y verifica:

#### Tab "Shots"
- [ ] Mensaje: "No hay shots"
- [ ] Botón rojo "Agregar" (FAB)

#### Tab "Granos"
- [ ] Mensaje: "No hay granos"
- [ ] Botón rojo "Agregar" (FAB)

#### Tab "Molinos"
- [ ] Mensaje: "No hay molinos"
- [ ] Botón rojo "Agregar" (FAB)

#### Tab "Perfiles"
- [ ] Mensaje: "No hay perfiles"
- [ ] Botón rojo "Agregar" (FAB)

#### Tab "Opciones" (Settings)
- [ ] TextField con label: "Dosis por Defecto"
- [ ] Valor pre-cargado: **18.0**
- [ ] TextField con label: "Ratio por Defecto"
- [ ] Valor pre-cargado: **2.0**
- [ ] Checkbox con label: "Autocompletar Shots"
- [ ] Estado: **ACTIVADO** (✓)
- [ ] Botón rojo: "Guardar"

---

## PASO 7: TEST RÁPIDO (1 minuto)

En tab "Opciones":

- [ ] Cambiar "Dosis por Defecto" a: `20.0`
- [ ] Tap botón rojo "Guardar"
- [ ] Ver snackbar (mensaje flotante) confirmando guardado
- [ ] Volver a tab "Opciones"
- [ ] Verificar que "Dosis por Defecto" ahora es: `20.0`
  - [ ] ✅ Si es 20.0 → Persistencia (DataStore) funciona
  - [ ] ❌ Si es 18.0 → Bug en guardado

---

## ✅ COMPLETADO

Si llegaste aquí y todos los checks están marcados:

- [x] ✅ Repositorio clonado
- [x] ✅ Gradle Sync exitoso
- [x] ✅ APK compilado
- [x] ✅ App instalada
- [x] ✅ App funcionando sin crashes
- [x] ✅ 5 tabs visibles
- [x] ✅ UI roja (#D32F2F)
- [x] ✅ Settings form con defaults
- [x] ✅ Persistencia funcionando

**🎉 ÉXITO: EspressoShots compila y ejecuta en Panda 2025.3.1**

---

## 🐛 ALGO SALIÓ MAL

Encuentra tu problema:

| Síntoma | Solución |
|---------|----------|
| "Gradle sync failed" | Ver PASO 2, paso "Si ves error" |
| "Cannot resolve symbol" | Build → Rebuild Project |
| "Compilation timeout" | Ver [BUILD_PANDA_2025.md](BUILD_PANDA_2025.md) "Problema 4" |
| Device no aparece | Ver [BUILD_PANDA_2025.md](BUILD_PANDA_2025.md) "Problema 6" |
| App crashes al abrir | Ver [BUILD_PANDA_2025.md](BUILD_PANDA_2025.md) "Problema 7" |
| APK 0 bytes o corrupted | Delete `app/build/` e intentar compilar de nuevo |

**Para otros problemas**: Ver [CLONE_AND_RUN.md](CLONE_AND_RUN.md) sección "🐛 TROUBLESHOOTING"

---

## 📚 REFERENCIAS

| Necesito | Ver |
|---------|-----|
| Instrucciones detalladas | [CLONE_AND_RUN.md](CLONE_AND_RUN.md) |
| Troubleshooting completo | [BUILD_PANDA_2025.md](BUILD_PANDA_2025.md) |
| Testing manual completo | [CHECKLIST.md](CHECKLIST.md) |
| Hacer commits y PR | [COMMIT_GUIDE.md](COMMIT_GUIDE.md) |
| Entender el código | [PROJECT_SUMMARY.md](PROJECT_SUMMARY.md) |

---

## 📞 Resumen

**Si todo funcionó:**
1. La app está compilada ✅
2. La app está funcionando ✅
3. Próximo paso: [COMMIT_GUIDE.md](COMMIT_GUIDE.md) (hacer 5 commits)

**Si algo no funcionó:**
1. Marca el problema arriba
2. Ve al documento correspondiente
3. Sigue instrucciones de troubleshooting

---

**Última actualización**: 14 Febrero 2026  
**Versión**: 1.0.0  
**Status**: ✅ READY
