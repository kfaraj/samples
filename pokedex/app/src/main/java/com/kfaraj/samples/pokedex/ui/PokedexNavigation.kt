package com.kfaraj.samples.pokedex.ui

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kfaraj.samples.pokedex.domain.FormatIdUseCase
import com.kfaraj.samples.pokedex.domain.FormatNameUseCase
import kotlinx.serialization.Serializable

/**
 * Describes the Pokédex route.
 */
@Serializable
data object PokedexRoute

/**
 * Adds the Pokédex destination.
 */
fun NavGraphBuilder.pokedexDestination(
    formatIdUseCase: FormatIdUseCase,
    formatNameUseCase: FormatNameUseCase,
    onClick: (item: PokedexItemUiState?) -> Unit
) {
    composable<PokedexRoute> {
        PokedexScreen(
            viewModel = hiltViewModel(),
            formatIdUseCase = formatIdUseCase,
            formatNameUseCase = formatNameUseCase,
            onClick = onClick
        )
    }
}
