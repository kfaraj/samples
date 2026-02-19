package com.kfaraj.samples.pokedex.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.kfaraj.samples.pokedex.data.Pokemon
import com.kfaraj.samples.pokedex.data.PokemonsRepository
import kotlinx.coroutines.flow.map
import org.koin.android.annotation.KoinViewModel

/**
 * Exposes the Pokédex UI state.
 */
@KoinViewModel
class PokedexViewModel(
    pokemonsRepository: PokemonsRepository
) : ViewModel() {

    /**
     * The stream of paged Pokédex items UI states.
     */
    val pagingData = pokemonsRepository.getPagingDataStream(PagingConfig(PAGE_SIZE))
        .map { pagingData ->
            pagingData.map { pokemon ->
                pokemon.toPokedexItemUiState()
            }
        }
        .cachedIn(viewModelScope)

    /**
     * Converts the model from the data layer to the UI layer.
     */
    private fun Pokemon.toPokedexItemUiState(): PokedexItemUiState {
        return PokedexItemUiState(
            id,
            name,
            sprite
        )
    }

    companion object {
        private const val PAGE_SIZE = 50
    }

}
