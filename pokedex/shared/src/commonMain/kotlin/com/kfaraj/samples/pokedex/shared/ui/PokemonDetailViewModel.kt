package com.kfaraj.samples.pokedex.shared.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kfaraj.samples.pokedex.shared.data.Pokemon
import com.kfaraj.samples.pokedex.shared.data.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

/**
 * Exposes the Pokémon detail UI state.
 */
@KoinViewModel
class PokemonDetailViewModel(
    savedStateHandle: SavedStateHandle,
    pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonDetailUiState())

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
