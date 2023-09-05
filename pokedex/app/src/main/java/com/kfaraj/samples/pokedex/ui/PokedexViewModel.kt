package com.kfaraj.samples.pokedex.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.kfaraj.samples.pokedex.data.Pokemon
import com.kfaraj.samples.pokedex.data.PokemonsRepository
import com.kfaraj.samples.pokedex.domain.GetSpriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Exposes the Pokédex UI state.
 */
@HiltViewModel
class PokedexViewModel @Inject constructor(
    pokemonsRepository: PokemonsRepository,
    getSpriteUseCase: GetSpriteUseCase
) : ViewModel() {

    /**
     * The stream of paged Pokédex items UI states.
     */
    val pagingData = pokemonsRepository.getPagingDataStream(PagingConfig(PAGE_SIZE))
        .map { pagingData ->
            pagingData.map { pokemon ->
                pokemon.toPokedexItemUiState(getSpriteUseCase)
            }
        }
        .cachedIn(viewModelScope)

    /**
     * Converts the model from the data layer to the UI layer.
     */
    private fun Pokemon.toPokedexItemUiState(
        getSpriteUseCase: GetSpriteUseCase
    ): PokedexItemUiState {
        return PokedexItemUiState(
            id,
            name,
            getSpriteUseCase(id)
        )
    }

    companion object {
        private const val PAGE_SIZE = 50
    }

}
