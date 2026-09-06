package com.kfaraj.samples.pokedex.data.pokemon.local

import androidx.paging.PagingSource

/**
 * Exposes Pokémon data from a local data source.
 */
internal interface PokemonLocalDataSource {

    /**
     * Inserts or updates Pokémon entities.
     */
    suspend fun upsertAll(pokemonEntities: List<PokemonEntity>)

    /**
     * Returns the Pokémon entity for the given [id].
     */
    suspend fun get(id: Int): PokemonEntity

    /**
     * Returns the source of paged Pokémon entities.
     */
    fun getPagingSource(): PagingSource<Int, PokemonEntity>

    /**
     * Returns the number of Pokémon entities.
     */
    suspend fun getCount(): Int

}
