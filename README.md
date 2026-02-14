# EspressoShots - Espresso Shot Tracker

Una aplicación Android moderna para registrar y gestionar shots de espresso, incluyendo granos, molinos, perfiles y configuración avanzada.

---

## 🚀 CÓMO EMPEZAR

### Opción 1: Guía Visual Rápida (5 minutos)
→ **[QUICKSTART_VISUAL.md](QUICKSTART_VISUAL.md)** - 5 pasos, 5 minutos

### Opción 2: Guía Detallada con Troubleshooting  
→ **[CLONE_AND_RUN.md](CLONE_AND_RUN.md)** ⭐ **GUÍA PRINCIPAL** - paso a paso completo

### Opción 3: Referencia Rápida
→ **[QUICK_CLONE.md](QUICK_CLONE.md)** - referencia en formato tabla

### Opción 4: Checklist Interactivo
→ **[CHECKLIST_CLONE_AND_BUILD.md](CHECKLIST_CLONE_AND_BUILD.md)** - checklist para seguir mientras compilas

---

## Características

- **Registro de Shots**: Registra cada shot con dosis, rendimiento, tiempo, temperatura y notas
- **Gestión de Granos**: Administra tus granos de café con fechas de tostado y compra
- **Molinos**: Cataloga molinos y sus ajustes por defecto
- **Perfiles**: Define perfiles de espresso para reproducibilidad
- **Opciones Configurables**: Ajusta valores por defecto y preferencias globales (DataStore)
- **Interfaz Moderna**: Jetpack Compose con Material  3
- **Persistencia**: Room (SQLite) para datos y DataStore para settings

## Arquitectura

- **MVVM**: ViewModel + Repository Pattern + DAOs
- **Navegación**: Bottom Navigation con tabs
- **DI**: Hilt para inyección de dependencias
- **Async**: Coroutines + Flows/StateFlow

### Estructura de Carpetas

```
app/src/main/
├── kotlin/com/jorgearenaza/espressoshots/
│   ├── data/
│   │   ├── db/          # Room entities, DAOs, Database
│   │   ├── repository/  # Repository pattern
│   │   ├── preferences/ # DataStore settings
│   │   └── datastore/   # AppSettingsManager
│   ├── di/              # Hilt dependency injection
│   ├── ui/
│   │   ├── screens/     # Pantallas principales
│   │   ├── components/  # Componentes reutilizables
│   │   ├── navigation/  # Navegación y destinos
│   │   ├── theme/       # Material3 theme (rojo primary)
│   │   └── viewmodel/   # ViewModels
│   ├── util/            # Utilidades y extensiones
│   └── MainActivity.kt
└── res/                 # Recursos (strings, colors, etc.)
```

## Modelo de Datos

### Entities (Room)

1. **BeanEntity** (`granos` table)
   - Tostador, Café/Variedad, Fechas de tostado y compra, Notas
   
2. **GrinderEntity** (`grinders` table)
   - Nombre (único), Ajuste por defecto, Notas
   
3. **ProfileEntity** (`profiles` table)
   - Nombre (único), Descripción, Parámetros (JSON simple)
   
4. **ShotEntity** (`shots` table)
   - Fecha, FK Bean, FK Grinder (opcional), FK Profile (opcional)
   - Dosis, Rendimiento, Ratio (calculado)
   - Tiempo, Temperatura, Ajuste de molienda, Notas, Rating

### Settings (DataStore)

- `defaultDoseG`: 18.0 (default)
- `defaultRatio`: 2.0 (default)
- `defaultGrinderId`: null (opcional)
- `defaultProfileId`: null (opcional)
- `autofillShots`: true (autocompletar en formularios)

## Cómo Compilar y Ejecutar

### 🚀 GUÍA COMPLETA PARA CLONAR Y EJECUTAR
**→ Ver [CLONE_AND_RUN.md](CLONE_AND_RUN.md)** para instrucciones detalladas paso a paso (Windows 11, macOS, Linux)

### Requisitos Mínimos
- Android Studio Panda 2025.3.1 o superior
- Gradle 8.4 (incluido en gradlew)
- minSdk: 24, targetSdk: 34, compileSdk: 34
- Kotlin 1.9.20
- OpenJDK 21.0.9 (incluido en Android Studio)
- 4GB+ RAM

### Compilar (Resumen)
```bash
./gradlew :app:assembleDebug
# APK generado: app/build/outputs/apk/debug/app-debug.apk
```

### Ejecutar
```bash
./gradlew :app:installDebug
# O desde Android Studio: Run → Run 'app'
```

## Pantallas y Flujo

1. **Shots**: Lista de shots, crear nuevo, editar, ver detalles
2. **Granos**: CRUD de granos con fechas y cálculo de frescura
3. **Molinos**: CRUD de molinos
4. **Perfiles**: CRUD de perfiles
5. **Opciones**: Configurar defaults, autofill, molino y perfil por defecto

## Pasos para Probar

1. **Crear un Molino**: Tab "Molinos" → Botón rojo "Agregar" → Nombre: "Timemore Whirly" → Guardar
2. **Crear un Grano**: Tab "Granos" → Botón rojo → Tostador: "Specialty Roaster" → Café: "Ethiopian Yirgacheffe" → Fecha de tostado: (hoy) → Guardar
3. **Registrar un Shot**: Tab "Shots" → Botón rojo → Seleccionar grano, molino → Dosis: 18g, Rendimiento: 36g (ratio se calcula) → Guardar
4. **Cambiar Settings**: Tab "Opciones" → Modificar Dosis/Ratio → Botón rojo "Guardar"
5. **Verificar Autofill**: Volver a Shots → Nuevo shot → Dosis y Ratio deberían estar prellenados con los valores guardados

---

## 📚 Documentación Disponible

| Documento | Propósito |
|-----------|-----------|
| **[CLONE_AND_RUN.md](CLONE_AND_RUN.md)** | 📥 Clonar repo y ejecutar en Android Studio Panda (EMPEZAR AQUÍ) |
| **[START_HERE.md](START_HERE.md)** | ⚡ Guía rápida 3 pasos |
| **[BUILD_PANDA_2025.md](BUILD_PANDA_2025.md)** | 🔨 Compilación detallada para Panda 2025.3.1 |
| **[BUILD_STATUS.md](BUILD_STATUS.md)** | ✅ Checklist estado actual (47 archivos .kt) |
| **[PROJECT_SUMMARY.md](PROJECT_SUMMARY.md)** | 🏗️ Arquitectura, paquetes, entidades, DAOs |
| **[QUICK_START.md](QUICK_START.md)** | ⚡ Setup rápido (alternativa) |
| **[COMMIT_GUIDE.md](COMMIT_GUIDE.md)** | 📦 5 commits lógicos para Pull Request |
| **[CHECKLIST.md](CHECKLIST.md)** | ☑️ Manual testing 5 tabs, colors, forms |
| **[INVENTORY.md](INVENTORY.md)** | 📋 Lista completa 87 archivos con LOC |
| **[DELIVERABLES.md](DELIVERABLES.md)** | ✨ Entregables finales y validación |
| **[RESUMEN_EJECUTIVO.md](RESUMEN_EJECUTIVO.md)** | 📝 Summary ejecutivo (español) |

---

## Pasos para Probar

## Componentes Clave

### AjusteMoliendaControl
Componente reutilizable para ajustes de molienda:
- Hoy: TextField + presets básicos (Espresso, Turbo, Filtro, etc.)
- Futuro (TODO): Slider numérico, stepper, presets pro

### RedFAB
Botón flotante de acción con color rojo (primary color de Material3)

### EmptyStateScreen
Pantalla vacía con mensaje y botón CTA cuando no hay datos

## Próximos Pasos (Fase Pro)

- Slider + stepper para ajuste de molienda numérico
- Gráficos y estadísticas
- Exportar/importar datos
- Sincronización en nube
- Fotos de shots
- Temporizador de espresso
- Recordatorios de limpieza

## Licencia

MIT License - Espresso project (@jorgearenaza)