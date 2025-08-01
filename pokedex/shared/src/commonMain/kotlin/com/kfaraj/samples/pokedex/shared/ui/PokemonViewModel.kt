package com.kfaraj.samples.pokedex.shared.ui

import com.kfaraj.samples.pokedex.shared.data.Pokemon
import com.kfaraj.samples.pokedex.shared.data.PokemonsRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Exposes the Pokémon UI state.
 */
class PokemonViewModel(
    id: Int,
    pokemonsRepository: PokemonsRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<PokemonUiState> =
        MutableStateFlow(viewModelScope, PokemonUiState())

    /**
     * The stream of Pokémon UI state.
     */
    @NativeCoroutinesState
    val uiState: StateFlow<PokemonUiState> =
        _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val pokemon = pokemonsRepository.get(id)
            _uiState.value = pokemon.toPokemonUiState()
        }
    }

    /**
     * Converts the model from the data layer to the UI layer.
     */
    private fun Pokemon.toPokemonUiState(): PokemonUiState {
        return PokemonUiState(
            id,
            name,
            sprite
        )
    }

}
