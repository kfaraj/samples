package com.kfaraj.samples.pokedex.feature.pokemon

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.kfaraj.samples.pokedex.data.pokemon.Pokemon
import com.kfaraj.samples.pokedex.data.pokemon.PokemonRepository
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.android.annotation.KoinViewModel

/**
 * Exposes the Pokémon detail UI state.
 */
@KoinViewModel
public class PokemonDetailViewModel internal constructor(
    savedStateHandle: SavedStateHandle,
    pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(viewModelScope, PokemonDetailUiState())

    /**
     * The stream of Pokémon detail UI state.
     */
    public val uiState: StateFlow<PokemonDetailUiState> =
        _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val id = savedStateHandle.toRoute<PokemonDetailRoute>().id
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
