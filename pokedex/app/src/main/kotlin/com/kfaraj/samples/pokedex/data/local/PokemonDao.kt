package com.kfaraj.samples.pokedex.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

/**
 * Exposes Pokémon data from a database.
 */
@Dao
interface PokemonDao {

    /**
     * Inserts or updates Pokémon entities.
     */
    @Upsert
    suspend fun upsertAll(pokemons: List<PokemonEntity>)

    /**
     * Returns the Pokémon entity for the given [id].
     */
    @Query("SELECT * FROM pokemons WHERE id = :id")
    suspend fun get(id: Int): PokemonEntity

    /**
     * Returns the source of paged Pokémon entities.
     */
    @Query("SELECT * FROM pokemons")
    fun getPagingSource(): PagingSource<Int, PokemonEntity>

    /**
     * Returns the number of Pokémon entities.
     */
    @Query("SELECT COUNT(*) FROM pokemons")
    suspend fun getCount(): Int

}
