package com.example.personajesapp.model

/**
 * Constantes de las categorías/roles disponibles para filtrar personajes.
 * Se usan tanto en el repositorio (al crear cada [Personaje]) como en la
 * barra lateral de filtros de la pantalla de selección.
 */
object Categorias {
    const val ALL = "ALL"
    const val TANK = "TANK"
    const val MAGE = "MAGE"
    const val ASSASSIN = "ASSASSIN"
    const val ADC = "ADC"
    const val SUPPORT = "SUPPORT"

    /** Orden en el que se deben mostrar los chips/botones del sidebar. */
    val ordenSidebar = listOf(ALL, TANK, MAGE, ASSASSIN, ADC, SUPPORT)
}
