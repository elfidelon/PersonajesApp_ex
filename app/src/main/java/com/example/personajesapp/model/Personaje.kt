package com.example.personajesapp.model

import androidx.annotation.DrawableRes

/**
 * Modelo de dominio que representa a un personaje seleccionable.
 *
 * @param id identificador único.
 * @param nombre nombre visible del personaje.
 * @param categoria rol de juego. Usar una de las constantes definidas en [Categorias].
 * @param descripcion texto descriptivo mostrado en la pantalla de detalle.
 * @param imagenPrincipal resource id (drawable) de la imagen principal/portada.
 * @param imagenes lista de resource ids (drawable) usados en el carrusel horizontal
 *                 de la pantalla de detalle. Si solo hay una imagen disponible,
 *                 puede contener un único elemento (igual a [imagenPrincipal]).
 * @param modelo3D ruta relativa dentro de `assets/` hacia el modelo 3D en formato
 *                 GLB/GLTF de este personaje (ej: "models/charmander.glb").
 *                 Puede ser una cadena vacía si todavía no se cuenta con modelo.
 * @param telefono número telefónico de contacto/soporte usado por el Intent ACTION_DIAL.
 * @param paginaWeb URL del sitio oficial usado por el Intent ACTION_VIEW.
 * @param ubicacion coordenadas en formato "lat,lng" usadas para construir el geo: URI.
 */
data class Personaje(
    val id: Int,
    val nombre: String,
    val categoria: String,
    val descripcion: String,
    @DrawableRes val imagenPrincipal: Int,
    val imagenes: List<Int>,
    val modelo3D: String,
    val telefono: String,
    val paginaWeb: String,
    val ubicacion: String
)
