package com.kfaraj.samples.pokedex.feature.pokemon

import androidx.paging.ItemSnapshotList
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingDataEvent
import androidx.paging.PagingDataPresenter
import androidx.paging.cachedIn
import androidx.paging.map
import com.kfaraj.samples.pokedex.data.pokemon.Pokemon
import com.kfaraj.samples.pokedex.data.pokemon.PokemonRepository
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

/**
 * Exposes the Pokémon list UI state.
 */
@KoinViewModel
public class PokemonListViewModel internal constructor(
    pokemonRepository: PokemonRepository
) : ViewModel() {

    /**
     * The stream of paged Pokémon list items UI states.
     */
    public val pagingData: Flow<PagingData<PokemonListItemUiState>> =
        pokemonRepository.getPagingDataStream(PagingConfig(PAGE_SIZE))
            .map { pagingData ->
                pagingData.map { pokemon ->
                    pokemon.toPokemonListItemUiState()
                }
            }
            .cachedIn(viewModelScope.coroutineScope)

    private val pagingDataPresenter = object : PagingDataPresenter<PokemonListItemUiState>() {
        override suspend fun presentPagingDataEvent(
            event: PagingDataEvent<PokemonListItemUiState>
        ) {
            updateSnapshot()
        }
    }

    private val _pagingDataSnapshot = MutableStateFlow(
        viewModelScope,
        pagingDataPresenter.snapshot()
    )

    /**
     * The stream of paged Pokémon list items UI states.
     */
    public val pagingDataSnapshot: StateFlow<ItemSnapshotList<PokemonListItemUiState>> =
        _pagingDataSnapshot.asStateFlow()

    init {
        viewModelScope.coroutineScope.launch {
            pagingData.collectLatest {
                pagingDataPresenter.collectFrom(it)
            }
        }
    }

    private fun updateSnapshot() {
        _pagingDataSnapshot.value = pagingDataPresenter.snapshot()
    }

    /**
     * Returns the item at [index].
     */
    public fun getItem(index: Int): PokemonListItemUiState? {
        return pagingDataPresenter[index]
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

    public companion object {
        private const val PAGE_SIZE = 50
    }

}
