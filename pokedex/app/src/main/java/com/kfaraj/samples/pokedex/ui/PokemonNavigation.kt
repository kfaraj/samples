package com.kfaraj.samples.pokedex.ui

import androidx.compose.animation.SharedTransitionScope
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

/**
 * Identifies the Pokémon destination.
 */
@Serializable
data class PokemonRoute(
    val id: Int
)

/**
 * Adds the Pokémon destination.
 */
fun NavGraphBuilder.pokemonDestination(
    sharedTransitionScope: SharedTransitionScope,
    onNavigateUp: () -> Unit
) {
    composable<PokemonRoute> {
        with(sharedTransitionScope) {
            PokemonScreen(
                animatedVisibilityScope = this@composable,
                viewModel = hiltViewModel(),
                onNavigateUp = onNavigateUp
            )
        }
    }
}

/**
 * Navigates to the Pokémon destination.
 */
fun NavController.navigateToPokemonDestination(
    id: Int
) {
    val route = PokemonRoute(id)
    navigate(route)
}
