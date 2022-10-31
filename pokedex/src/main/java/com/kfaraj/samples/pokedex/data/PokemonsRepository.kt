package com.kfaraj.samples.pokedex.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.kfaraj.samples.pokedex.data.local.PokemonEntity
import com.kfaraj.samples.pokedex.data.local.PokemonsLocalDataSource
import com.kfaraj.samples.pokedex.data.remote.PokemonsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Exposes Pokémon data.
 */
@Singleton
class PokemonsRepository @Inject constructor(
    private val pokemonsRemoteDataSource: PokemonsRemoteDataSource,
    private val pokemonsLocalDataSource: PokemonsLocalDataSource
) {

    /**
     * Returns Pokémon data for the given [id].
     */
    suspend fun get(id: Int): Pokemon {
        return pokemonsLocalDataSource.get(id).toPokemon()
    }

    /**
     * Returns the stream of paged Pokémon data.
     */
    fun getPagingDataStream(config: PagingConfig): Flow<PagingData<Pokemon>> {
        return Pager(
            config,
            null,
            PokemonsRemoteMediator(pokemonsRemoteDataSource, pokemonsLocalDataSource)
        ) {
            pokemonsLocalDataSource.getPagingSource()
        }.flow
            .map { pagingData ->
                pagingData.map { pokemonEntity ->
                    pokemonEntity.toPokemon()
                }
            }
    }

    /**
     * Converts the model from the local data source to the data layer.
     */
    private fun PokemonEntity.toPokemon(): Pokemon {
        return Pokemon(
            id,
            name
        )
    }

}
