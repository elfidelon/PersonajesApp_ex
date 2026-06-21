package com.example.personajesapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ArenaDarkColorScheme = darkColorScheme(
    primary = ArenaPrimary,
    onPrimary = ArenaOnDark,
    secondary = ArenaSecondary,
    onSecondary = ArenaBackgroundDark,
    tertiary = ArenaTertiary,
    onTertiary = ArenaOnDark,
    background = ArenaBackgroundDark,
    onBackground = ArenaOnDark,
    surface = ArenaSurfaceDark,
    onSurface = ArenaOnDark,
    surfaceVariant = ArenaSurfaceVariantDark,
    onSurfaceVariant = ArenaOnDarkMuted,
    error = ArenaError,
    onError = ArenaOnDark
)

// La app está pensada con identidad visual oscura "estilo videojuego" fija,
// pero se deja preparado un esquema claro por si se requiere alternar en
// el futuro (ej. accesibilidad).
private val ArenaLightColorScheme = lightColorScheme(
    primary = ArenaPrimaryVariant,
    secondary = ArenaSecondary,
    tertiary = ArenaTertiary
)

@Composable
fun PersonajesAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    forceArenaDark: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (forceArenaDark || darkTheme) ArenaDarkColorScheme else ArenaLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = ArenaTypography,
        content = content
    )
}
