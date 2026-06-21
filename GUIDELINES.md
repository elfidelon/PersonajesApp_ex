# GUIDELINES — Personajes Arena (Selector de Personajes)

Proyecto Android nativo en **Kotlin + Jetpack Compose + Material 3 + Navigation
Compose**, orientación **landscape forzada**, `minSdk 26`. Selector de
personajes estilo videojuego con pantalla de selección (grid + filtros +
búsqueda) y pantalla de detalle (info + carrusel + modelo 3D + acciones
rápidas vía Intents).

---

## 1. Estructura del proyecto

```
PersonajesApp/
├── build.gradle.kts                  (proyecto)
├── settings.gradle.kts
├── gradle.properties
├── gradle/
│   └── libs.versions.toml            (catálogo de versiones)
└── app/
    ├── build.gradle.kts              (módulo app)
    ├── proguard-rules.pro
    └── src/main/
        ├── AndroidManifest.xml
        ├── assets/
        │   └── models/                <- modelos .glb (3D)
        ├── res/
        │   ├── drawable/               <- imágenes de personajes + ícono
        │   ├── mipmap-anydpi-v26/      <- ícono adaptativo
        │   └── values/                 <- strings.xml, themes.xml
        └── java/com/example/personajesapp/
            ├── MainActivity.kt
            ├── model/                   <- Personaje.kt, Categorias.kt
            ├── data/                    <- PersonajeRepository.kt
            ├── navigation/               <- Screen.kt, NavGraph.kt
            ├── ui/
            │   ├── theme/                <- Color.kt, Type.kt, Theme.kt
            │   ├── screens/              <- CharacterListScreen.kt, CharacterDetailScreen.kt
            │   └── components/           <- todos los componentes reutilizables
            └── utils/                    <- IntentUtils.kt
```

Esta separación (`model / data / ui / navigation / components`) es la misma
que pediste y facilita que un evaluador ubique rápidamente cada
responsabilidad.

---

## 2. Datos de ejemplo incluidos

El repositorio (`PersonajeRepository.kt`) ya viene precargado con **9
personajes de ejemplo**, usando las imágenes y modelos `.glb` que subiste en
`modelos.zip`:

| Personaje  | Categoría | Imagen | Modelo 3D incluido |
|------------|-----------|--------|---------------------|
| Bulbasaur  | TANK      | ✅      | ❌ (no estaba en el zip) |
| Charmander | ADC       | ✅      | ✅ |
| Pikachu    | MAGE      | ✅      | ❌ (no estaba en el zip) |
| Ekans      | ASSASSIN  | ✅      | ✅ |
| Diglett    | ASSASSIN  | ✅      | ❌ (no estaba en el zip) |
| Paras      | SUPPORT   | ✅      | ✅ |
| Oddish     | SUPPORT   | ✅      | ✅ |
| Caterpie   | ADC       | ✅      | ✅ |
| Macha      | TANK      | ✅      | ✅ |

Las descripciones de cada personaje son texto original de ambientación
(no se copió ningún texto de fuentes externas), pensado solo como relleno
de ejemplo para tu examen. Tanto las imágenes como los modelos GLB que
subiste ya están copiados dentro del proyecto (`res/drawable/` y
`assets/models/`), renombrados en minúsculas (requisito de Android para
recursos).

**Importante:** todo el set de imágenes/modelos que usaste es un set de
ejemplo. Si tu profesor pide personajes originales/propios, reemplaza estos
assets siguiendo la sección 3 y 4, y ajusta `PersonajeRepository.kt`.

---

## 3. Cómo agregar / cambiar IMÁGENES

1. Copia tu imagen (`.png`, `.jpg` o `.webp`) dentro de:
   `app/src/main/res/drawable/`
2. El nombre del archivo debe ir **en minúsculas**, sin espacios ni acentos,
   solo letras, números y `_` (regla de Android). Ej: `mi_personaje.png`.
3. Android genera automáticamente la referencia `R.drawable.mi_personaje`.
4. En `PersonajeRepository.kt`, usa esa referencia en `imagenPrincipal` y/o
   dentro de la lista `imagenes` (para el carrusel, si tienes varias skins
   del mismo personaje agrégalas todas a esa lista).

```kotlin
Personaje(
    id = 10,
    nombre = "MiPersonaje",
    categoria = Categorias.MAGE,
    descripcion = "...",
    imagenPrincipal = R.drawable.mi_personaje,
    imagenes = listOf(R.drawable.mi_personaje, R.drawable.mi_personaje_skin2),
    modelo3D = "",
    telefono = "+52 477 000 00 00",
    paginaWeb = "https://ejemplo.com",
    ubicacion = "21.1250,-101.6860"
)
```

---

## 4. Modelos 3D — YA INTEGRADOS con SceneView (render real)

A diferencia de la primera versión de este proyecto (que solo mostraba un
placeholder), `CharacterModelViewer.kt` ahora **renderiza el modelo `.glb`
real** usando la librería **SceneView** (Filament + Jetpack Compose):

- Dependencia agregada en `app/build.gradle.kts`:
  `implementation("io.github.sceneview:sceneview:3.5.2")` (solo el módulo
  3D, sin ARCore, por lo que no se piden permisos de cámara).
- El usuario puede **orbitar y hacer zoom con el dedo** sobre el modelo
  (gracias a `rememberCameraManipulator()`).
- Si el `.glb` del personaje trae una animación embebida, se reproduce
  automáticamente (`autoAnimate = true`).
- Para los personajes sin `.glb` en los datos de ejemplo (Bulbasaur,
  Pikachu, Diglett — el zip que subiste no traía modelo para ellos) se
  sigue mostrando el placeholder animado, indicando que falta el archivo.

**Para agregar un modelo nuevo:**
1. Copia tu `.glb` en `app/src/main/assets/models/`.
2. En `PersonajeRepository.kt`, pon `modelo3D = "models/tu_archivo.glb"`.
3. Listo — `CharacterModelViewer` lo detecta automáticamente y lo renderiza.

**⚠️ Aviso de transparencia importante:** SceneView tuvo una reescritura
mayor de su API muy recientemente (rumbo 100% Compose-native) y sigue
evolucionando rápido. Este código se basó en el README oficial vigente al
momento de generarlo, pero no pudo compilarse en un entorno real (sin
Android SDK) para confirmarlo al 100%. Si al sincronizar ves algún
`import` en rojo:
1. Pon el cursor sobre el símbolo → `Alt+Enter` (⌥+Enter en Mac) →
   "Import" — Android Studio resuelve la ruta correcta automáticamente.
2. Si algo no cuadra, la fuente más actualizada es el propio README:
   https://github.com/SceneView/sceneview/blob/main/README.md

Si prefieres no arriesgarte con una librería externa tan reciente para tu
examen, puedes revertir fácilmente: borra el bloque `if (hayModelo) { Real3DModel(...) }`
en `CharacterModelViewer.kt` y deja solo `ModelPlaceholder(...)` — la app
sigue funcionando exactamente igual, solo sin el render 3D real.

---

## 5. Versiones y dependencias usadas

Definidas en `gradle/libs.versions.toml` (todas estables y verificadas como
mutuamente compatibles):

| Componente | Versión |
|---|---|
| Android Gradle Plugin (AGP) | 8.5.2 |
| Kotlin | 2.0.21 |
| Compose BOM | 2024.09.00 |
| Navigation Compose | 2.8.0 |
| SceneView (render 3D) | 3.5.2 |
| compileSdk / targetSdk | 34 |
| minSdk | 26 (Android Oreo) |

Se usa el **plugin oficial de Compose para Kotlin 2.0**
(`org.jetbrains.kotlin.plugin.compose`), por lo que **no** se configura
`composeOptions { kotlinCompilerExtensionVersion = ... }` en
`app/build.gradle.kts` (esa forma es la antigua, de Kotlin < 2.0, y
entraría en conflicto con el plugin nuevo).

---

## 6. Orientación Landscape forzada

Configurado en `AndroidManifest.xml`:
- `android:screenOrientation="sensorLandscape"` en `MainActivity` → permite
  landscape normal e invertido (mejor UX) pero **bloquea por completo el
  portrait**.
- `android:configChanges="orientation|screenSize|..."` para evitar
  recreaciones innecesarias de la Activity al rotar entre las dos variantes
  de landscape.

No se necesita ninguna configuración adicional en Compose para esto.

---

## 7. Los 4 Intents implementados (pantalla de detalle)

Todos centralizados en `utils/IntentUtils.kt`:

1. **Llamar** → `Intent.ACTION_DIAL` con `tel:<numero>` (abre el marcador,
   no llama directamente, por lo que no requiere el permiso `CALL_PHONE`).
2. **Sitio Oficial** → `Intent.ACTION_VIEW` con un URI `https://...` (abre
   el navegador).
3. **Compartir** → `Intent.ACTION_SEND` con `text/plain`, mostrado mediante
   `Intent.createChooser(...)`.
4. **Ubicación** → `Intent.ACTION_VIEW` con URI `geo:lat,lng?q=...` (abre
   Google Maps u otra app de mapas instalada).

Las 4 acciones verifican con `resolveActivity(...)` que exista una app
capaz de responder antes de lanzar el Intent, mostrando un `Toast` si no la
hay (por ejemplo, un emulador sin Google Maps instalado).

---

## 8. ✅ Checklist de compilación

Antes de compilar en Android Studio, verifica:

- [ ] Abriste la **carpeta raíz** `PersonajesApp/` (no la carpeta `app/`)
      como proyecto en Android Studio.
- [ ] Android Studio descargó el AGP 8.5.2, Kotlin 2.0.21 y el resto de
      dependencias del `libs.versions.toml` (necesita conexión a internet
      la primera vez, para `google()` y `mavenCentral()`).
- [ ] El SDK 34 (compileSdk/targetSdk) está instalado en tu SDK Manager.
- [ ] `File > Sync Project with Gradle Files` se ejecutó sin errores.
- [ ] No hay imports rojos (todas las dependencias se resolvieron).
- [ ] `Build > Make Project` termina con `BUILD SUCCESSFUL`.
- [ ] Ejecutas en un emulador/dispositivo con **API 26 o superior**.
- [ ] El emulador/dispositivo tiene una app de mapas y un navegador
      instalados si vas a probar los botones "Ubicación" y "Sitio Oficial".

> Nota de transparencia: este proyecto fue generado y revisado línea por
> línea fuera de Android Studio (sin acceso al Android SDK / Gradle en el
> entorno de generación), por lo que la verificación se hizo mediante
> revisión manual exhaustiva de imports, tipos, navegación y balance de
> sintaxis — no mediante una compilación real con `gradlew build`. Se
> recomienda hacer el primer `Gradle Sync` con calma y revisar el panel
> "Build" ante cualquier advertencia. Esto aplica especialmente a la
> integración de **SceneView** (sección 4): es una librería externa que
> tuvo una reescritura muy reciente de su API, así que si ves algún
> `import` en rojo al sincronizar, usa `Alt+Enter` sobre el símbolo para
> que Android Studio lo resuelva automáticamente.

---

## 9. ✅ Checklist de evaluación (para maximizar la calificación)

- [ ] **Arquitectura**: paquetes separados `model/data/ui/navigation` ✔
- [ ] **Kotlin + Compose + Material 3** ✔
- [ ] **Navigation Compose** con rutas tipadas (`Screen.kt`) y argumentos
      (`characterId`) ✔
- [ ] **Landscape forzado**, portrait bloqueado ✔
- [ ] **Pantalla 1**: fondo personalizado, top bar con logo, búsqueda en
      tiempo real, grid responsive (`LazyVerticalGrid` adaptativo), sidebar
      de categorías con scroll independiente y estado visual de selección ✔
- [ ] **Pantalla 2**: layout izquierda/centro/derecha, carrusel
      (`HorizontalPager`), visor de modelo 3D preparado, acciones rápidas ✔
- [ ] **4 Intents**: `ACTION_DIAL`, `ACTION_VIEW` (web), `ACTION_SEND`,
      geo `ACTION_VIEW` ✔
- [ ] **Animaciones**: transición entre pantallas (slide+fade),
      entrada escalonada de las cards, escala al presionar, color animado
      en filtros ✔
- [ ] **Loading state** (`LoadingState.kt`) y **Empty state**
      (`EmptyState.kt`) ✔
- [ ] **Manifest**: `<queries>` declaradas, `exported` correcto, sin
      permisos peligrosos innecesarios ✔
- [ ] **Componente `CharacterModelViewer`**: renderiza modelos `.glb`
      reales con SceneView/Filament (orbit + zoom táctil), con placeholder
      de respaldo para personajes sin modelo ✔

---

## 10. Próximos pasos sugeridos (opcionales, no necesarios para aprobar)

- Integrar SceneView/Filament para renderizar los `.glb` reales en vez del
  placeholder.
- Agregar más imágenes por personaje en `imagenes` para un carrusel con
  múltiples skins.
- Mover `PersonajeRepository` a una fuente de datos real (Room, API REST)
  si el profesor pide persistencia.
