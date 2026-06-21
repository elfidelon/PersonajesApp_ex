package com.example.personajesapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.personajesapp.R
import com.example.personajesapp.model.Personaje
import com.example.personajesapp.ui.components.ArenaBackground
import com.example.personajesapp.ui.components.CharacterModelViewer
import com.example.personajesapp.ui.components.EmptyState
import com.example.personajesapp.ui.components.ImageCarousel
import com.example.personajesapp.ui.components.QuickActionsColumn
import com.example.personajesapp.ui.components.colorParaCategoria
import com.example.personajesapp.ui.theme.ArenaCardDark
import com.example.personajesapp.ui.theme.ArenaOnDark
import com.example.personajesapp.ui.theme.ArenaOnDarkMuted

@Composable
fun CharacterDetailScreen(
    personaje: Personaje?,
    onBackClick: () -> Unit
) {
    ArenaBackground {
        Column(modifier = Modifier.fillMaxSize()) {
            DetailTopBar(titulo = personaje?.nombre ?: "Detalle", onBackClick = onBackClick)

            if (personaje == null) {
                EmptyState(modifier = Modifier.fillMaxSize())
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    InfoPanel(
                        personaje = personaje,
                        modifier = Modifier.fillMaxHeight().weight(0.9f)
                    )

                    CenterPanel(
                        personaje = personaje,
                        modifier = Modifier.fillMaxHeight().weight(1.3f)
                    )

                    QuickActionsColumn(
                        personaje = personaje,
                        modifier = Modifier.fillMaxHeight().weight(0.8f)
                    )
                }
            }
        }
    }
}

@Composable
private fun DetailTopBar(titulo: String, onBackClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(start = 4.dp, top = 8.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null, tint = ArenaOnDark)
        }
        Text(text = titulo.uppercase(), style = MaterialTheme.typography.titleLarge, color = ArenaOnDark)
    }
}

@Composable
private fun InfoPanel(personaje: Personaje, modifier: Modifier = Modifier) {
    val colorAcento = colorParaCategoria(personaje.categoria)
    Column(
        modifier = modifier.clip(RoundedCornerShape(18.dp)).background(ArenaCardDark).verticalScroll(rememberScrollState()).padding(18.dp)
    ) {
        Text(text = personaje.nombre, style = MaterialTheme.typography.headlineMedium, color = ArenaOnDark)
        Box(modifier = Modifier.padding(top = 8.dp, bottom = 16.dp).clip(RoundedCornerShape(8.dp)).background(colorAcento.copy(alpha = 0.2f)).padding(horizontal = 10.dp, vertical = 4.dp)) {
            Text(text = personaje.categoria, style = MaterialTheme.typography.labelLarge, color = colorAcento)
        }
        Text(text = personaje.descripcion, style = MaterialTheme.typography.bodyLarge, color = ArenaOnDark)
    }
}

@Composable
private fun CenterPanel(personaje: Personaje, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.1f) // Carrusel reducido
                .clip(RoundedCornerShape(18.dp))
        ) {
            ImageCarousel(
                imagenes = personaje.imagenes,
                contentDescriptionBase = personaje.nombre,
                modifier = Modifier.fillMaxSize()
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.6f) // Visor 3D agrandado
        ) {
            CharacterModelViewer(
                modeloPath = personaje.modelo3D,
                modifier = Modifier.fillMaxSize(),
                categoria = personaje.categoria
            )
        }
    }
}
