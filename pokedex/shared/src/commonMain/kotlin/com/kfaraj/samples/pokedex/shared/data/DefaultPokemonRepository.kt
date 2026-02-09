package com.kfaraj.samples.pokedex.shared.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.kfaraj.samples.pokedex.shared.data.local.PokemonEntity
import com.kfaraj.samples.pokedex.shared.data.local.PokemonLocalDataSource
import com.kfaraj.samples.pokedex.shared.data.remote.PokemonRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

/**
 * Provides a default implementation of [PokemonRepository].
 */
@Single
internal class DefaultPokemonRepository(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val pokemonLocalDataSource: PokemonLocalDataSource
) : PokemonRepository {

    override suspend fun get(id: Int): Pokemon {
        return pokemonLocalDataSource.get(id).toPokemon()
    }

    override fun getPagingDataStream(config: PagingConfig): Flow<PagingData<Pokemon>> {
        return Pager(
            config,
            null,
            PokemonRemoteMediator(pokemonRemoteDataSource, pokemonLocalDataSource)
        ) {
            pokemonLocalDataSource.getPagingSource()
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
            name,
            sprite
        )
    }

}
