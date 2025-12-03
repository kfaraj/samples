package com.kfaraj.samples.pokedex.ui

import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

/**
 * Identifies the Pokédex destination.
 */
@Serializable
data object PokedexRoute

/**
 * Adds the Pokédex destination.
 */
fun NavGraphBuilder.pokedexDestination(
    sharedTransitionScope: SharedTransitionScope,
    onItemClick: (item: PokedexItemUiState?) -> Unit
) {
    composable<PokedexRoute> {
        with(sharedTransitionScope) {
            PokedexScreen(
                animatedVisibilityScope = this@composable,
                viewModel = koinViewModel(),
                onItemClick = onItemClick
            )
        }
    }
}
