package com.kfaraj.samples.pokedex.feature.pokemon

import androidx.paging.ItemSnapshotList
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

/**
 * Instantiates [PokemonListViewModel].
 */
public object PokemonListViewModelFactory : KoinComponent {

    /**
     * Creates a new instance of [PokemonListViewModel].
     */
    public fun create(
    ): PokemonListViewModel {
        return get()
    }

}

/**
 * The current value of [PokemonListViewModel.pagingDataSnapshot].
 */
public val PokemonListViewModel.pagingDataSnapshotValue: ItemSnapshotList<PokemonListItemUiState>
    get() = pagingDataSnapshot.value
