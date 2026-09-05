package com.kfaraj.samples.pokedex.feature.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ItemSnapshotList
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.asItemSnapshotListFlow
import androidx.paging.map
import com.kfaraj.samples.pokedex.data.pokemon.Pokemon
import com.kfaraj.samples.pokedex.data.pokemon.PokemonRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.koin.core.annotation.KoinViewModel

/**
 * Exposes the Pokémon list UI state.
 */
@KoinViewModel
internal class PokemonListViewModel(
    pokemonRepository: PokemonRepository
) : ViewModel() {

    private val pager = Pager(
        PagingConfig(PAGE_SIZE),
        null,
        pokemonRepository.getRemoteMediator()
    ) {
        pokemonRepository.getPagingSource()
    }

    /**
     * The stream of paged Pokémon list items UI states.
     */
    val itemSnapshotList: StateFlow<ItemSnapshotList<PokemonListItemUiState>> =
        pager.flow
            .map { pagingData ->
                pagingData.map { pokemon ->
                    pokemon.toPokemonListItemUiState()
                }
            }
            .asItemSnapshotListFlow()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000L),
                ItemSnapshotList(0, 0, emptyList())
            )

    /**
     * Loads a page at the end of current loaded data.
     */
    fun append() {
        pager.append()
    }

    /**
     * Loads a page at the start of current loaded data.
     */
    fun prepend() {
        pager.prepend()
    }

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
