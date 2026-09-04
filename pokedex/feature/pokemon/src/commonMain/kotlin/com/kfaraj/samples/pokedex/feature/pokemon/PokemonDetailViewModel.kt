package com.kfaraj.samples.pokedex.feature.pokemon

import com.kfaraj.samples.pokedex.data.pokemon.Pokemon
import com.kfaraj.samples.pokedex.data.pokemon.PokemonRepository
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.KoinViewModel

/**
 * Exposes the Pokémon detail UI state.
 */
@KoinViewModel
public class PokemonDetailViewModel internal constructor(
    @InjectedParam key: PokemonDetailKey,
    pokemonRepository: PokemonRepository
) : ViewModel() {

    /**
     * The stream of Pokémon detail UI state.
     */
    public val uiState: StateFlow<PokemonDetailUiState>
        field = MutableStateFlow(viewModelScope, PokemonDetailUiState())

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
