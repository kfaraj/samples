package com.kfaraj.samples.pokedex.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kfaraj.samples.pokedex.data.Pokemon
import com.kfaraj.samples.pokedex.data.PokemonsRepository
import com.kfaraj.samples.pokedex.domain.GetSpriteUseCase
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
    pokemonsRepository: PokemonsRepository,
    getSpriteUseCase: GetSpriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonUiState())

    /**
     * The stream of Pokémon UI state.
     */
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val id = PokemonFragmentArgs.fromSavedStateHandle(savedStateHandle).id
            val pokemon = pokemonsRepository.get(id)
            _uiState.value = pokemon.toPokemonUiState(getSpriteUseCase)
        }
    }

    /**
     * Converts the model from the data layer to the UI layer.
     */
    private fun Pokemon.toPokemonUiState(
        getSpriteUseCase: GetSpriteUseCase
    ): PokemonUiState {
        return PokemonUiState(
            id,
            name,
            getSpriteUseCase(id)
        )
    }

}
