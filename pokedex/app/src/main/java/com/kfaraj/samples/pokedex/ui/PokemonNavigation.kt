package com.kfaraj.samples.pokedex.ui

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kfaraj.samples.pokedex.domain.FormatIdUseCase
import com.kfaraj.samples.pokedex.domain.FormatNameUseCase
import kotlinx.serialization.Serializable

/**
 * Describes the Pokémon route.
 */
@Serializable
data class PokemonRoute(
    val id: Int
)

/**
 * Adds the Pokémon destination.
 */
fun NavGraphBuilder.pokemonDestination(
    formatIdUseCase: FormatIdUseCase,
    formatNameUseCase: FormatNameUseCase
) {
    composable<PokemonRoute> {
        PokemonScreen(
            viewModel = hiltViewModel(),
            formatIdUseCase = formatIdUseCase,
            formatNameUseCase = formatNameUseCase
        )
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
