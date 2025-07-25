package com.kfaraj.samples.pokedex.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.kfaraj.samples.pokedex.data.Pokemon
import com.kfaraj.samples.pokedex.data.PokemonsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Exposes the Pokémon UI state.
 */
@HiltViewModel
class PokemonViewModel @Inject constructor(
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
            val id = savedStateHandle.toRoute<PokemonRoute>().id
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
