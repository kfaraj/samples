package com.kfaraj.samples.pokedex.data.pokemon

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.RemoteMediator

/**
 * Exposes Pokémon data.
 */
public interface PokemonRepository {

    /**
     * Returns Pokémon data for the given [id].
     */
    public suspend fun get(id: Int): Pokemon

    /**
     * Returns the [PagingSource] of Pokémon data.
     */
    public fun getPagingSource(): PagingSource<Int, Pokemon>

    /**
     * Returns the [RemoteMediator] of Pokémon data.
     */
    @ExperimentalPagingApi
    public fun getRemoteMediator(): RemoteMediator<Int, Pokemon>

}
