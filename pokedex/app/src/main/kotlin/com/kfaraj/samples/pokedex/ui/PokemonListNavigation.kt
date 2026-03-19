package com.kfaraj.samples.pokedex.ui

import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

/**
 * Identifies the Pokémon list destination.
 */
@Serializable
data object PokemonListRoute

/**
 * Adds the Pokémon list destination.
 */
fun NavGraphBuilder.pokemonListDestination(
    sharedTransitionScope: SharedTransitionScope,
    onItemClick: (itemId: Int?) -> Unit
) {
    composable<PokemonListRoute> {
        with(sharedTransitionScope) {
            PokemonListScreen(
                animatedVisibilityScope = this@composable,
                viewModel = koinViewModel(),
                onItemClick = { item ->
                    onItemClick(item?.id)
                }
            )
        }
    }
}
