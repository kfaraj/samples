package com.kfaraj.samples.pokedex.feature.pokemon

import androidx.paging.ItemSnapshotList
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

/**
 * TODO
 */
public object PokemonListViewModelFactory : KoinComponent {

    /**
     * TODO
     */
    public fun create(
    ): PokemonListViewModel {
        return get()
    }

}

/**
 * TODO
 */
public val PokemonListViewModel.pagingDataSnapshotValue: ItemSnapshotList<PokemonListItemUiState>
    get() = pagingDataSnapshot.value
