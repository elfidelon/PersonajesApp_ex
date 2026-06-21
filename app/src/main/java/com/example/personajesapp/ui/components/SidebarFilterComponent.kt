package com.example.personajesapp.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.personajesapp.model.Categorias
import com.example.personajesapp.ui.theme.ArenaAdcColor
import com.example.personajesapp.ui.theme.ArenaAllColor
import com.example.personajesapp.ui.theme.ArenaAssassinColor
import com.example.personajesapp.ui.theme.ArenaMageColor
import com.example.personajesapp.ui.theme.ArenaOnDarkMuted
import com.example.personajesapp.ui.theme.ArenaSupportColor
import com.example.personajesapp.ui.theme.ArenaSurfaceVariantDark
import com.example.personajesapp.ui.theme.ArenaTankColor

/** Devuelve el color de acento asociado a cada categoría de filtro. */
fun colorParaCategoria(categoria: String): Color = when (categoria) {
    Categorias.TANK -> ArenaTankColor
    Categorias.MAGE -> ArenaMageColor
    Categorias.ASSASSIN -> ArenaAssassinColor
    Categorias.ADC -> ArenaAdcColor
    Categorias.SUPPORT -> ArenaSupportColor
    else -> ArenaAllColor
}

/**
 * Sidebar lateral izquierdo con los filtros de categoría. Tiene scroll
 * independiente del grid principal y resalta visualmente el filtro activo
 * con color de acento, fondo y borde.
 */
@Composable
fun CategorySidebar(
    categoriaSeleccionada: String,
    onCategoriaSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .width(140.dp)
            .background(ArenaSurfaceVariantDark)
            .padding(vertical = 16.dp, horizontal = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "FILTROS",
            style = MaterialTheme.typography.labelSmall,
            color = ArenaOnDarkMuted,
            modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
        )

        Categorias.ordenSidebar.forEach { categoria ->
            FilterChipItem(
                categoria = categoria,
                seleccionado = categoria == categoriaSeleccionada,
                onClick = { onCategoriaSelected(categoria) }
            )
        }
    }
}

@Composable
private fun FilterChipItem(
    categoria: String,
    seleccionado: Boolean,
    onClick: () -> Unit
) {
    val colorAcento = colorParaCategoria(categoria)
    val backgroundColor by animateColorAsState(
        targetValue = if (seleccionado) colorAcento.copy(alpha = 0.18f) else Color.Transparent,
        animationSpec = tween(220),
        label = "filterBackground"
    )
    val borderColor by animateColorAsState(
        targetValue = if (seleccionado) colorAcento else ArenaOnDarkMuted.copy(alpha = 0.25f),
        animationSpec = tween(220),
        label = "filterBorder"
    )
    val textColor = if (seleccionado) colorAcento else ArenaOnDarkMuted

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .border(
                border = BorderStroke(width = 1.dp, color = borderColor),
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(onClick = onClick)
            .padding(vertical = 10.dp, horizontal = 12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = categoria,
            style = MaterialTheme.typography.labelLarge,
            color = textColor
        )
    }
}
