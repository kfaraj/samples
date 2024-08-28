package com.kfaraj.samples.pokedex.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kfaraj.samples.pokedex.domain.FormatIdUseCase
import com.kfaraj.samples.pokedex.domain.FormatNameUseCase
import com.kfaraj.samples.pokedex.ui.PokemonScreen
import kotlinx.serialization.Serializable

@Serializable
data class PokemonRoute(
    val id: Int
)

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

fun NavController.navigateToPokemon(id: Int) {
    navigate(PokemonRoute(id))
}
