package com.kfaraj.samples.pokedex.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kfaraj.samples.pokedex.domain.FormatIdUseCase
import com.kfaraj.samples.pokedex.domain.FormatNameUseCase
import com.kfaraj.samples.pokedex.ui.PokedexItemUiState
import com.kfaraj.samples.pokedex.ui.PokedexScreen
import kotlinx.serialization.Serializable

@Serializable
object PokedexRoute

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
