package com.example.personajesapp.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.personajesapp.data.PersonajeRepository
import com.example.personajesapp.ui.screens.CharacterDetailScreen
import com.example.personajesapp.ui.screens.CharacterListScreen

private const val ANIM_DURATION_MS = 380

/**
 * Grafo de navegación principal de la app. Contiene las dos pantallas:
 * la lista/selección de personajes y el detalle de un personaje.
 */
@Composable
fun PersonajesNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.CharacterList.route
    ) {
        composable(
            route = Screen.CharacterList.route,
            exitTransition = {
                fadeOut(animationSpec = tween(ANIM_DURATION_MS)) +
                    slideOutHorizontally(
                        animationSpec = tween(ANIM_DURATION_MS),
                        targetOffsetX = { fullWidth -> -fullWidth / 4 }
                    )
            },
            popEnterTransition = {
                fadeIn(animationSpec = tween(ANIM_DURATION_MS)) +
                    slideInHorizontally(
                        animationSpec = tween(ANIM_DURATION_MS),
                        initialOffsetX = { fullWidth -> -fullWidth / 4 }
                    )
            }
        ) {
            CharacterListScreen(
                personajes = PersonajeRepository.personajes,
                onPersonajeClick = { personaje ->
                    navController.navigate(Screen.CharacterDetail.createRoute(personaje.id))
                }
            )
        }

        composable(
            route = Screen.CharacterDetail.route,
            arguments = listOf(
                navArgument(Screen.CharacterDetail.ARG_CHARACTER_ID) {
                    type = NavType.IntType
                }
            ),
            enterTransition = {
                fadeIn(animationSpec = tween(ANIM_DURATION_MS)) +
                    slideInHorizontally(
                        animationSpec = tween(ANIM_DURATION_MS),
                        initialOffsetX = { fullWidth -> fullWidth / 4 }
                    )
            },
            popExitTransition = {
                fadeOut(animationSpec = tween(ANIM_DURATION_MS)) +
                    slideOutHorizontally(
                        animationSpec = tween(ANIM_DURATION_MS),
                        targetOffsetX = { fullWidth -> fullWidth / 4 }
                    )
            }
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt(Screen.CharacterDetail.ARG_CHARACTER_ID) ?: -1
            val personaje = PersonajeRepository.obtenerPorId(characterId)

            CharacterDetailScreen(
                personaje = personaje,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
