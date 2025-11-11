package com.kfaraj.samples.pokedex.shared.data.local

import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

/**
 * Exposes Pokémon data from a local data source.
 */
@Factory
class PokemonsLocalDataSource(
    private val pokemonDao: PokemonDao
) {

    /**
     * Inserts or updates Pokémon entities.
     */
    suspend fun upsertAll(pokemons: List<PokemonEntity>) {
        pokemonDao.upsertAll(pokemons)
    }

    /**
     * Returns the Pokémon entity for the given [id].
     */
    suspend fun get(id: Int): PokemonEntity {
        return pokemonDao.get(id)
    }

    /**
     * Returns the source of paged Pokémon entities.
     */
    fun getPagingSource(limit: Int, offset: Int): Flow<List<PokemonEntity>> {
        return pokemonDao.getPagingSource(limit, offset)
    }

    /**
     * Returns the number of Pokémon entities.
     */
    suspend fun getCount(): Int {
        return pokemonDao.getCount()
    }

}
