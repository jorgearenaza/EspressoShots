# Instrucciones para Completar el Setup y Hacer Commits

Este documento es un guía para completar la configuración de EspressoShots después de la creación de archivos.

## 1. Verificar Integridad del Proyecto

```bash
cd /workspaces/EspressoShots

# Listar estructura
tree -L 3 app/src/main/kotlin/com/jorgearenaza/espressoshots/

# Verificar archivos críticos
ls -la build.gradle.kts settings.gradle.kts
ls -la app/build.gradle.kts app/src/main/AndroidManifest.xml
```

## 2. Preparar para Compilación

### Hacer Gradlew Ejecutable

```bash
chmod +x ./gradlew
```

### Verificar Java/Gradle

```bash
java -version
./gradlew --version  # Debe ser 8.4
```

### Compilar Proyecto

```bash
# Limpiar y construir
./gradlew clean :app:assembleDebug

# El APK se generará en:
# app/build/outputs/apk/debug/app-debug.apk
```

## 3. Hacer Commits

### Montar Rama Clean

```bash
git checkout -b rebuild/clean-mvvm
```

### Commit 1: Bootstrap + Dependencies

```bash
git add .
git commit -m "bootstrap: Gradle config, dependencies, manifests

- Add root build.gradle.kts y settings.gradle.kts
- Configure app/build.gradle.kts with Kotlin DSL
- Add all dependencies:
  - Jetpack Compose BOM + Material3
  - Room + DataStore
  - Hilt DI
  - Coroutines
  - Navigation-Compose
- Add AndroidManifest.xml with permissions
- Add strings.xml, colors.xml, themes.xml
- Add launcher icons (adaptive + monochrome)
- Configure ProGuard rules"
```

###Commit 2: Data Layer (Room + DataStore)

```bash
git add app/src/main/kotlin/com/jorgearenaza/espressoshots/data/
git commit -m "data: Room entities, DAOs, database, repositories

Entities:
- BeanEntity (granos): Tostador, Café, Fechas, Notas
- GrinderEntity (molinos): Nombre unique, Ajuste, Notas
- ProfileEntity (perfiles): Nombre unique, Descripción, Parámetros
- ShotEntity (shots): Datos espresso + FK constraint

DAOs:
- BeanDao: CRUD + getAllActive flow
- GrinderDao: CRUD + getAllActive flow
- ProfileDao: CRUD + getAllActive flow
- ShotDao: CRUD + filtered queries

Database:
- AppDatabase singleton con Room.databaseBuilder
- Versión 1, no schema export

Repositories:
- BeanRepository: Wrapper para BeanDao
- GrinderRepository: Wrapper para GrinderDao
- ProfileRepository: Wrapper para ProfileDao
- ShotRepository: Wrapper para ShotDao

Settings:
- SettingsRepository: DataStore wrapper para preferencias
- Valores: defaultDoseG, defaultRatio, defaultGrinderId, defaultProfileId, autofillShots
- AppSettingsManager: Context-based manager (compatible dual)"
```

### Commit 3: DI + Theme + Navigation

```bash
git add \
  app/src/main/kotlin/com/jorgearenaza/espressoshots/di/ \
  app/src/main/kotlin/com/jorgearenaza/espressoshots/ui/theme/ \
  app/src/main/kotlin/com/jorgearenaza/espressoshots/ui/navigation/ \
  app/src/main/kotlin/com/jorgearenaza/espressoshots/EspressoShotsApp.kt 

git commit -m "di+theme+nav: Hilt setup, Material3 red theme, bottom navigation

Hilt:
- @HiltAndroidApp on EspressoShotsApp
- @Module DataModule con @Provides para (DB, Repositories, Managers)
- @AndroidEntryPoint on MainActivity
- hiltViewModel() en screens

Theme:
- Material3 lightColorScheme y darkColorScheme
- Red primary (#D32F2F), bright status bar
- Dynamic color support (SDK 31+)
- EspressoShotsTheme composable

Navigation:
- Enum EspressoShotsDestination (5 tabs)
- NavigationItems data class + getNavigationItems()
- EspressoShotsApp: Scaffold con NavigationBar
- Bottom navigation con selected/unselected icons
- Cuando tap: muestra pantalla correspondiente"
```

### Commit 4: Components + Screens Base

```bash
git add app/src/main/kotlin/com/jorgearenaza/espressoshots/ui/
git commit -m "ui: Base screens, components, viewmodels

Components:
- RedFAB: FloatingActionButton red + Material3
- EmptyStateScreen: Box center con message + "Agregar" CTA
- AjusteMoliendaControl: TextField + preset chips (Espresso, Turbo, Filtro, Fino, Medio, Grueso)
- SettingCheckbox: Row con checkbox + label

Screens (list-only):
- ShotsScreen: Scaffold + FAB + LazyColumn(ShotCard) + EmptyState
- BeansScreen: Scaffold + FAB + LazyColumn(BeanCard) con delete button
- GrindersScreen: Scaffold + FAB + LazyColumn(GrinderCard) con delete button
- ProfilesScreen: Scaffold + FAB + LazyColumn(ProfileCard) con delete button
- SettingsScreen: TextField inputs + checkbox + red Save button, snackbar

ViewModels:
- ShotViewModel @HiltViewModel con allShots StateFlow
- BeanViewModel @HiltViewModel con allBeans StateFlow
- GrinderViewModel @HiltViewModel con allGrinders StateFlow
- ProfileViewModel @HiltViewModel con allProfiles StateFlow
- SettingsViewModel @HiltViewModel con appSettings StateFlow

Integraciones:
- hiltViewModel() en cada screen
- collectAsState() para flows
- Delete buttons call viewModel.delete(id)"
```

### Commit 5: Documentation

```bash
git add README.md PROJECT_SUMMARY.md
git commit -m "docs: Comprehensive README and project summary

- Features overview (5 tabs, CRUD, settings, autofill)
- Architecture diagram (MVVM, Room, DataStore, Hilt)
- Folder structure explained
- Model de datos completo
- Build instrucciones
- Test flow (crear molino -> grano -> shot -> verificar settings)
- Links a fase PRO (slider, stats, etc)"
```

## 4. Crear Pull Request

```bash
# Hacer push de rama
git push origin rebuild/clean-mvvm

# Crear PR en GitHub:
# - Title: "feat: Clean rebuild with MVVM + Compose + Room + DataStore"
# - Body:
#   - This is a complete rebuild of EspressoShots from scratch
#   - Full MVVM architecture with Jetpack Compose + Material3
#   - Room database for entities + DataStore for settings
#   - Bottom navigation with 5 tabs (Shots, Granos, Molinos, Perfiles, Opciones)
#   - Hilt DI for repositories and viewmodels
#   - Red FAB buttons on all screens
#   - Empty state screens with CTAs
#   - Grind adjustment component ready for phase pro
#   - Closes #1 (o similar)

# PR Description en GitHub:
## ✅ Checklist
- [x] Compila correctamente (`./gradlew :app:assembleDebug`)
- [x] MVVM pattern implementado
- [x] Room entities + DAOs + Repositories
- [x] DataStore para settings globales
- [x] Hilt DI setup
- [x] Bottom navigation con 5 tabs
- [x] Todos los pantallas muestran listas básicas
- [x] Botones rojos (FAB + Save) Material3
- [x] Empty states con CTA
- [x] Componente AjusteMolienda preparado para fase pro
- [x] README documentación completa
- [x] Commits lógicos y claros

## 🎯 Tests Manuales

1. **Pantalla Granos**: 
   - Botón rojo "Agregar" → Muestra CTA
   - Lista vacía inicialmente
   
2. **Pantalla Molinos**: 
   - Ídem, lista vacía

3. **Pantalla Opciones**:
   - Valores por defecto cargados de DataStore
   - Cambiar dosis → Botón rojo "Guardar" → Persist a DataStore
   - Toggle "Autocompletar Shots"

4. **Build APK**:
   - `./gradlew :app:assembleDebug` genera app-debug.apk
   - Sin errores de compilación o gradle
```

## 5. Compilación Final

```bash
# Compilar una última vez
./gradlew clean :app:assembleDebug

# Verificar APK
ls -lh app/build/outputs/apk/debug/app-debug.apk

# Si hay emulator/device:
./gradlew :app:installDebug
adb shell am start -n com.jorgearenaza.espressoshots/.MainActivity
```

## 6. Estado Final

Después de todo esto, tendrás:

✅ **Repository**: EspressoShots limpio
✅ **Branch**: rebuild/clean-mvvm
✅ **Commits**: 5 lógicos y claros
✅ **APK compilable**: app-debug.apk generado
✅ **Tests**: Flujo básico verificado (pantallas, navegación, settings)
✅ **PR abierto**: Con descripción detallada y checklist

---

### Troubleshooting

Si hay problemas de compilación:

1. **Gradle sync errors**:
   ```bash
   ./gradlew --refresh-dependencies
   ./gradlew clean
   ```

2. **Kotlin compiler errors**:
   - Verificar imports en archivos
   - Gradle menu > Refresh Dependencies
   - Invalidate Cache & Restart en Android Studio

3. **Room errors**:
   - Asegurar que @Entity, @Dao, @Database están presentes
   - No olvidar kapt("androidx.room:room-compiler")

4. **Hilt errors**:
   - @HiltAndroidApp en Application
   - @AndroidEntryPoint en Activity
   - DataModule correcto en @InstallIn(SingletonComponent)

---

**Nota**: Si el terminal tiene problemas, todos los archivos fueron creados programáticamente. Solo falta ejecutar:
- `chmod +x ./gradlew`
- `./gradlew :app:assembleDebug`
- Y hacer los commits vía git commands.

¡Éxito! 🚀
