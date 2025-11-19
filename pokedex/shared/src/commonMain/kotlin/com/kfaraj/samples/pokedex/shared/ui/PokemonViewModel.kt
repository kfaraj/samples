package com.kfaraj.samples.pokedex.shared.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kfaraj.samples.pokedex.shared.data.Pokemon
import com.kfaraj.samples.pokedex.shared.data.PokemonsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

/**
 * Exposes the Pokémon UI state.
 */
@KoinViewModel
class PokemonViewModel(
    savedStateHandle: SavedStateHandle,
    pokemonsRepository: PokemonsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonUiState())

    /**
     * The stream of Pokémon UI state.
     */
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val id = requireNotNull(savedStateHandle.get<Int>("id"))
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
