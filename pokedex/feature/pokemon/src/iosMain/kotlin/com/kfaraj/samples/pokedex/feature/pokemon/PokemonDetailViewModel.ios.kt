package com.kfaraj.samples.pokedex.feature.pokemon

import androidx.lifecycle.SavedStateHandle
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

/**
 * TODO
 */
public object PokemonDetailViewModelFactory : KoinComponent {

    /**
     * TODO
     */
    public fun create(
        id: Int
    ): PokemonDetailViewModel {
        val savedStateHandle = SavedStateHandle(mapOf("id" to id))
        return get { parametersOf(savedStateHandle) }
    }

}

/**
 * TODO
 */
public val PokemonDetailViewModel.uiStateValue: PokemonDetailUiState
    get() = uiState.value
