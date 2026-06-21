# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.

# Si se habilita minify en el futuro, mantener los modelos de datos
# para que la reflexión (si se usa) no falle.
-keep class com.example.personajesapp.model.** { *; }
