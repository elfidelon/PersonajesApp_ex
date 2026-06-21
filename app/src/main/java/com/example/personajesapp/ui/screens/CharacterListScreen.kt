package com.example.personajesapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.personajesapp.model.Categorias
import com.example.personajesapp.model.Personaje
import com.example.personajesapp.ui.components.ArenaBackground
import com.example.personajesapp.ui.components.CategorySidebar
import com.example.personajesapp.ui.components.CharacterCard
import com.example.personajesapp.ui.components.EmptyState
import com.example.personajesapp.ui.components.GameTopBar
import com.example.personajesapp.ui.components.SearchField

/**
 * PANTALLA 1 — Selección de personajes.
 *
 * Estructura (pensada para landscape):
 *  - [GameTopBar] arriba con el logo del juego.
 *  - Fila principal: [CategorySidebar] a la izquierda (scroll propio) y a
 *    la derecha el campo de búsqueda + [LazyVerticalGrid] (scroll propio).
 *
 * El filtrado por categoría y por texto de búsqueda se recalcula en cada
 * recomposición a partir de [personajes]; al ser una lista pequeña en
 * memoria no se requiere `derivedStateOf`, pero se deja la estructura
 * lista para escalar fácilmente si la fuente de datos crece.
 */
@Composable
fun CharacterListScreen(
    personajes: List<Personaje>,
    onPersonajeClick: (Personaje) -> Unit
) {
    var query by remember { mutableStateOf("") }
    var categoriaSeleccionada by remember { mutableStateOf(Categorias.ALL) }

    val personajesFiltrados = remember(personajes, query, categoriaSeleccionada) {
        personajes.filter { personaje ->
            val coincideCategoria =
                categoriaSeleccionada == Categorias.ALL || personaje.categoria == categoriaSeleccionada
            val coincideBusqueda =
                query.isBlank() || personaje.nombre.contains(query, ignoreCase = true)
            coincideCategoria && coincideBusqueda
        }
    }

    ArenaBackground {
        Column(modifier = Modifier.fillMaxSize()) {
            GameTopBar()

            Row(modifier = Modifier.fillMaxSize()) {
                CategorySidebar(
                    categoriaSeleccionada = categoriaSeleccionada,
                    onCategoriaSelected = { categoriaSeleccionada = it }
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                ) {
                    SearchField(
                        query = query,
                        onQueryChange = { query = it },
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (personajesFiltrados.isEmpty()) {
                        EmptyState(modifier = Modifier.fillMaxSize())
                    } else {
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(minSize = 150.dp),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 16.dp),
                            contentPadding = PaddingValues(bottom = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(14.dp),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            items(
                                items = personajesFiltrados,
                                key = { it.id }
                            ) { personaje ->
                                var visible by remember(personaje.id) { mutableStateOf(false) }
                                LaunchedEffect(personaje.id) { visible = true }

                                AnimatedVisibility(
                                    visible = visible,
                                    enter = fadeIn(animationSpec = tween(300)) +
                                        scaleIn(initialScale = 0.85f, animationSpec = tween(300))
                                ) {
                                    CharacterCard(
                                        personaje = personaje,
                                        onClick = { onPersonajeClick(personaje) }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
