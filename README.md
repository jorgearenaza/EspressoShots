# EspressoShots - Espresso Shot Tracker

Una aplicación Android moderna para registrar y gestionar shots de espresso, incluyendo granos, molinos, perfiles y configuración avanzada.

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

### Requisitos
- Android Studio Panda o superior
- Gradle 8.4
- minSdk: 24, targetSdk: 34
- Kotlin 1.9.20

### Compilar
```bash
./gradlew :app:assembleDebug
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