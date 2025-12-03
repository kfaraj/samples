package com.kfaraj.samples.pokedex.shared.ui

import androidx.paging.ItemSnapshotList
import androidx.paging.PagingConfig
import androidx.paging.PagingDataEvent
import androidx.paging.PagingDataPresenter
import androidx.paging.cachedIn
import androidx.paging.map
import com.kfaraj.samples.pokedex.shared.data.Pokemon
import com.kfaraj.samples.pokedex.shared.data.PokemonsRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.coroutineScope
import com.rickclephas.kmp.observableviewmodel.launch
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
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
        .cachedIn(viewModelScope.coroutineScope)

    private val pagingDataPresenter = object : PagingDataPresenter<PokedexItemUiState>() {
        override suspend fun presentPagingDataEvent(event: PagingDataEvent<PokedexItemUiState>) {
            updateSnapshot()
        }
    }

    private val _pagingDataSnapshot =
        MutableStateFlow(viewModelScope, pagingDataPresenter.snapshot())

    /**
     * The stream of paged Pokédex items UI states.
     */
    @NativeCoroutinesState
    val pagingDataSnapshot: StateFlow<ItemSnapshotList<PokedexItemUiState>> =
        _pagingDataSnapshot.asStateFlow()

    init {
        viewModelScope.launch {
            pagingData.collectLatest {
                pagingDataPresenter.collectFrom(it)
            }
        }
    }

    /**
     * TODO
     */
    private fun updateSnapshot() {
        _pagingDataSnapshot.value = pagingDataPresenter.snapshot()
    }

    /**
     * TODO
     */
    fun get(index: Int): PokedexItemUiState? {
        return pagingDataPresenter[index]
    }

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
