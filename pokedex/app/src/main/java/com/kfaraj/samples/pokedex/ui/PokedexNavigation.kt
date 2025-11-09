package com.kfaraj.samples.pokedex.ui

import androidx.compose.animation.SharedTransitionScope
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

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
                viewModel = hiltViewModel(),
                onItemClick = onItemClick
            )
        }
    }
}
