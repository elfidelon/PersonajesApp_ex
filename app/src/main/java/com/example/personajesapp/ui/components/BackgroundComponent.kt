package com.example.personajesapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.personajesapp.ui.theme.ArenaBackgroundDark
import com.example.personajesapp.ui.theme.ArenaPrimary
import com.example.personajesapp.ui.theme.ArenaSecondary
import com.example.personajesapp.ui.theme.ArenaSurfaceDark

/**
 * Fondo personalizado "estilo videojuego AAA": degradado radial oscuro con
 * acentos de color y una rejilla sutil para dar sensación de profundidad.
 *
 * No depende de ninguna imagen externa, por lo que funciona desde el
 * primer momento. Si más adelante quieres usar un fondo con imagen propia:
 * 1) coloca tu archivo en `res/drawable/bg_arena.jpg` (o .png/.webp)
 * 2) reemplaza el contenido de este Box por:
 *    Image(
 *        painter = painterResource(R.drawable.bg_arena),
 *        contentDescription = null,
 *        contentScale = ContentScale.Crop,
 *        modifier = Modifier.fillMaxSize()
 *    )
 */
@Composable
fun ArenaBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        ArenaSurfaceDark,
                        ArenaBackgroundDark,
                        Color.Black
                    ),
                    radius = 1400f
                )
            )
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val glowColor1 = ArenaPrimary.copy(alpha = 0.10f)
            val glowColor2 = ArenaSecondary.copy(alpha = 0.08f)

            drawCircle(
                color = glowColor1,
                radius = size.maxDimension * 0.35f,
                center = Offset(x = size.width * 0.15f, y = size.height * 0.1f)
            )
            drawCircle(
                color = glowColor2,
                radius = size.maxDimension * 0.3f,
                center = Offset(x = size.width * 0.9f, y = size.height * 0.95f)
            )
        }

        content()
    }
}
