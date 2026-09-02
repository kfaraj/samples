package com.kfaraj.samples.pokedex.feature.pokemon

import androidx.lifecycle.SavedStateHandle
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

/**
 * Instantiates [PokemonDetailViewModel].
 */
public object PokemonDetailViewModelFactory : KoinComponent {

    /**
     * Creates a new instance of [PokemonDetailViewModel].
     */
    public fun create(
        id: Int
    ): PokemonDetailViewModel {
        val key = PokemonDetailKey(id)
        return get {
            parametersOf(key)
        }
    }

}

/**
 * The current value of [PokemonDetailViewModel.uiState].
 */
public val PokemonDetailViewModel.uiStateValue: PokemonDetailUiState
    get() = uiState.value
