package com.kfaraj.samples.pokedex.shared.data.local

import app.cash.sqldelight.async.coroutines.awaitAsOne
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

/**
 * Exposes Pokémon data from a local data source.
 */
class PokemonsLocalDataSource(
    private val pokemonQueries: PokemonEntityQueries,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    /**
     * Inserts or updates Pokémon entities.
     */
    suspend fun upsertAll(pokemons: List<PokemonEntity>) {
        pokemonQueries.transaction {
            for (pokemon in pokemons) {
                pokemonQueries.upsert(pokemon)
            }
        }
    }

    /**
     * Returns the Pokémon entity for the given [id].
     */
    suspend fun get(id: Int): PokemonEntity {
        return pokemonQueries.get(id.toLong()).awaitAsOne()
    }

    /**
     * Returns the source of paged Pokémon entities.
     */
    fun getPagingSource(): Flow<List<PokemonEntity>> {
        return pokemonQueries.getPagingSource().asFlow().mapToList(ioDispatcher)
    }

    /**
     * Returns the number of Pokémon entities.
     */
    suspend fun getCount(): Int {
        return pokemonQueries.getCount().awaitAsOne().toInt()
    }

}
