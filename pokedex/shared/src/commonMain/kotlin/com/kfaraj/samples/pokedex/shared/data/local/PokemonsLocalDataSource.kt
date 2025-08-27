package com.kfaraj.samples.pokedex.shared.data.local

import app.cash.sqldelight.async.coroutines.awaitAsOne
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.kfaraj.samples.pokedex.shared.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

/**
 * Exposes Pokémon data from a local data source.
 */
@Factory
class PokemonsLocalDataSource(
    private val pokemonQueries: PokemonEntityQueries,
    @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher
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
    fun getPagingSource(limit: Int, offset: Int): Flow<List<PokemonEntity>> {
        return pokemonQueries.getPagingSource(limit.toLong(), offset.toLong()).asFlow()
            .mapToList(ioDispatcher)
    }

    /**
     * Returns the number of Pokémon entities.
     */
    suspend fun getCount(): Int {
        return pokemonQueries.getCount().awaitAsOne().toInt()
    }

}
