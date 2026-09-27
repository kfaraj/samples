package com.kfaraj.samples.pokedex.feature.pokemon

import androidx.paging.ItemSnapshotList
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.asItemSnapshotListFlow
import androidx.paging.map
import com.kfaraj.samples.pokedex.data.pokemon.Pokemon
import com.kfaraj.samples.pokedex.data.pokemon.PokemonRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.KoinViewModel

/**
 * Exposes the Pokémon list UI state.
 */
@KoinViewModel
public class PokemonListViewModel internal constructor(
    pokemonRepository: PokemonRepository
) : ViewModel() {

    private val pagingConfig = PagingConfig(
        pageSize = PAGE_SIZE
    )
    private val pager = Pager(
        config = pagingConfig,
        remoteMediator = pokemonRepository.getRemoteMediator(),
        pagingSourceFactory = { pokemonRepository.getPagingSource() }
    )

    /**
     * The stream of paged Pokémon list items UI states.
     */
    @NativeCoroutinesState
    public val itemSnapshotList: StateFlow<ItemSnapshotList<PokemonListItemUiState>> =
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
     * Indicates that the item at [index] has been accessed.
     */
    public fun itemAccessed(index: Int) {
        if (index <= itemSnapshotList.value.placeholdersBefore +
            pagingConfig.prefetchDistance
        ) {
            pager.prepend()
        }
        if (index >= itemSnapshotList.value.size - itemSnapshotList.value.placeholdersAfter - 1 -
            pagingConfig.prefetchDistance
        ) {
            pager.append()
        }
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
