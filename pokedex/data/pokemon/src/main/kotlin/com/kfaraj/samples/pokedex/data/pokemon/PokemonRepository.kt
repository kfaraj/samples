package com.kfaraj.samples.pokedex.data.pokemon

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

/**
 * Exposes Pokémon data.
 */
public interface PokemonRepository {

    /**
     * Returns Pokémon data for the given [id].
     */
    public suspend fun get(id: Int): Pokemon

    /**
     * Returns the stream of paged Pokémon data.
     */
    public fun getPagingDataStream(config: PagingConfig): Flow<PagingData<Pokemon>>

}
