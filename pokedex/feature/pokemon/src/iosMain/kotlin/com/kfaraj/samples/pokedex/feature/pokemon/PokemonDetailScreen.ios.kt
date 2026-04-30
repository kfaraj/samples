package com.kfaraj.samples.pokedex.feature.pokemon

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.ui.window.ComposeUIViewController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kfaraj.samples.pokedex.core.ui.theme.AppTheme
import platform.UIKit.UIViewController

/**
 * Displays the Pokémon detail UI state on the screen.
 */
public fun PokemonDetailViewController(
    id: Int,
    onNavigateUp: () -> Unit
): UIViewController = ComposeUIViewController {
    AppTheme {
        SharedTransitionLayout {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = PokemonDetailRoute(id)
            ) {
                pokemonDetailDestination(
                    sharedTransitionScope = this@SharedTransitionLayout,
                    onNavigateUp = onNavigateUp
                )
            }
        }
    }
}
