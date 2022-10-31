package com.kfaraj.samples.pokedex.data.local

import androidx.paging.PagingSource
import javax.inject.Inject

/**
 * Exposes Pokémon data from a local data source.
 */
class PokemonsLocalDataSource @Inject constructor(
    private val pokemonDao: PokemonDao
) {

    /**
     * Inserts or replaces Pokémon entities.
     */
    suspend fun insertAll(pokemons: List<PokemonEntity>) {
        pokemonDao.insertAll(pokemons)
    }

    /**
     * Returns the source of paged Pokémon entities.
     */
    fun getPagingSource(): PagingSource<Int, PokemonEntity> {
        return pokemonDao.getPagingSource()
    }

    /**
     * Returns the number of Pokémon entities.
     */
    suspend fun getCount(): Int {
        return pokemonDao.getCount()
    }

}
