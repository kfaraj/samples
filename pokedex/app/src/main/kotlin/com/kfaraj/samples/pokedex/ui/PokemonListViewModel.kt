package com.kfaraj.samples.pokedex.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.kfaraj.samples.pokedex.data.Pokemon
import com.kfaraj.samples.pokedex.data.PokemonRepository
import kotlinx.coroutines.flow.map
import org.koin.android.annotation.KoinViewModel

/**
 * Exposes the Pokémon list UI state.
 */
@KoinViewModel
class PokemonListViewModel(
    pokemonRepository: PokemonRepository
) : ViewModel() {

    /**
     * The stream of paged Pokémon list items UI states.
     */
    val pagingData = pokemonRepository.getPagingDataStream(PagingConfig(PAGE_SIZE))
        .map { pagingData ->
            pagingData.map { pokemon ->
                pokemon.toPokemonListItemUiState()
            }
        }
        .cachedIn(viewModelScope)

    /**
     * Converts the model from the data layer to the UI layer.
     */
    private fun Pokemon.toPokemonListItemUiState(): PokemonListItemUiState {
        return PokemonListItemUiState(
            id,
            name,
            sprite
        )
    }

    companion object {
        private const val PAGE_SIZE = 50
    }

}
