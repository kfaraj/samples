package com.kfaraj.samples.pokedex.feature.pokemon

import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

/**
 * Identifies the Pokémon list destination.
 */
@Serializable
public data object PokemonListRoute

/**
 * Adds the Pokémon list destination.
 */
public fun NavGraphBuilder.pokemonListDestination(
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
