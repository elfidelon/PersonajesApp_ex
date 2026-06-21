package com.example.personajesapp.navigation

/**
 * Definición centralizada de las rutas de navegación de la app.
 * Usar sealed class evita errores de tipeo con strings sueltos y facilita
 * construir rutas con argumentos de forma segura.
 */
sealed class Screen(val route: String) {

    data object CharacterList : Screen("character_list")

    data object CharacterDetail : Screen("character_detail/{characterId}") {
        const val ARG_CHARACTER_ID = "characterId"

        fun createRoute(characterId: Int): String = "character_detail/$characterId"
    }
}
