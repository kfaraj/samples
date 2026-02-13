package com.kfaraj.samples.pokedex.shared.ui

import androidx.lifecycle.SavedStateHandle
import com.kfaraj.samples.pokedex.shared.data.Pokemon
import com.kfaraj.samples.pokedex.shared.data.PokemonRepository
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import kotlinx.coroutines.flow.asStateFlow
import org.koin.android.annotation.KoinViewModel

/**
 * Exposes the Pokémon detail UI state.
 */
@KoinViewModel
class PokemonDetailViewModel(
    savedStateHandle: SavedStateHandle,
    pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(viewModelScope, PokemonDetailUiState())

    /**
     * The stream of Pokémon detail UI state.
     */
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val id = requireNotNull(savedStateHandle.get<Int>("id"))
            val pokemon = pokemonRepository.get(id)
            _uiState.value = pokemon.toPokemonDetailUiState()
        }
    }

    /**
     * Converts the model from the data layer to the UI layer.
     */
    private fun Pokemon.toPokemonDetailUiState(): PokemonDetailUiState {
        return PokemonDetailUiState(
            id,
            name,
            sprite
        )
    }

}
