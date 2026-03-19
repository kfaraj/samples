package com.kfaraj.samples.pokedex.data

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

/**
 * Exposes Pokémon data.
 */
interface PokemonRepository {

    /**
     * Returns Pokémon data for the given [id].
     */
    suspend fun get(id: Int): Pokemon

    /**
     * Returns the stream of paged Pokémon data.
     */
    fun getPagingDataStream(config: PagingConfig): Flow<PagingData<Pokemon>>

}
