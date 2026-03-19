package com.kfaraj.samples.pokedex.feature.pokemon

import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

/**
 * Identifies the Pokémon detail destination.
 */
@Serializable
public data class PokemonDetailRoute(
    val id: Int
)

/**
 * Adds the Pokémon detail destination.
 */
public fun NavGraphBuilder.pokemonDetailDestination(
    sharedTransitionScope: SharedTransitionScope,
    onNavigateUp: () -> Unit
) {
    composable<PokemonDetailRoute> {
        with(sharedTransitionScope) {
            PokemonDetailScreen(
                animatedVisibilityScope = this@composable,
                viewModel = koinViewModel(),
                onNavigateUp = onNavigateUp
            )
        }
    }
}

/**
 * Navigates to the Pokémon detail destination.
 */
public fun NavController.navigateToPokemonDetailDestination(
    id: Int
) {
    val route = PokemonDetailRoute(id)
    navigate(route)
}
