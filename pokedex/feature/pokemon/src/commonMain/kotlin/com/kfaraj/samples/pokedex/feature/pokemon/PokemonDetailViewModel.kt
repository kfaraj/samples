package com.kfaraj.samples.pokedex.feature.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kfaraj.samples.pokedex.data.pokemon.Pokemon
import com.kfaraj.samples.pokedex.data.pokemon.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.KoinViewModel

/**
 * Exposes the Pokémon detail UI state.
 */
@KoinViewModel
internal class PokemonDetailViewModel(
    @InjectedParam key: PokemonDetailKey,
    pokemonRepository: PokemonRepository
) : ViewModel() {

    /**
     * The stream of Pokémon detail UI state.
     */
    val uiState: StateFlow<PokemonDetailUiState>
        field = MutableStateFlow(PokemonDetailUiState())

    init {
        viewModelScope.launch {
            val pokemon = pokemonRepository.get(key.id)
            uiState.value = pokemon.toPokemonDetailUiState()
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
