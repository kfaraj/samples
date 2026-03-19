package com.kfaraj.samples.pokedex.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.kfaraj.samples.pokedex.data.local.PokemonEntity
import com.kfaraj.samples.pokedex.data.local.PokemonLocalDataSource
import com.kfaraj.samples.pokedex.data.remote.NamedApiResource
import com.kfaraj.samples.pokedex.data.remote.Pokemon.sprites
import com.kfaraj.samples.pokedex.data.remote.PokemonRemoteDataSource
import com.kfaraj.samples.pokedex.data.remote.PokemonSpecies.id
import com.kfaraj.samples.pokedex.data.remote.PokemonSpecies.names
import com.kfaraj.samples.pokedex.data.remote.PokemonSpecies.varieties
import io.ktor.client.plugins.ResponseException
import java.io.IOException

/**
 * Incrementally loads Pokémon data from a remote data source into a local data source.
 */
@ExperimentalPagingApi
class PokemonRemoteMediator(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val pokemonLocalDataSource: PokemonLocalDataSource
) : RemoteMediator<Int, PokemonEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        return try {
            val offset = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(true)
                LoadType.APPEND -> pokemonLocalDataSource.getCount()
            }
            val response = pokemonRemoteDataSource.getPokemonSpecies(state.config.pageSize, offset)
            val pokemonEntities = response
                .results
                .map { result ->
                    result.toPokemonEntity()
                }
            pokemonLocalDataSource.upsertAll(pokemonEntities)
            MediatorResult.Success(response.next == null)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: ResponseException) {
            MediatorResult.Error(e)
        }
    }

    override suspend fun initialize(): InitializeAction {
        return if (pokemonLocalDataSource.getCount() == 0) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    /**
     * Converts the model from the remote data source to the local data source.
     */
    private fun NamedApiResource.toPokemonEntity(): PokemonEntity {
        return PokemonEntity(
            id,
            names.first().name,
            varieties.first().pokemon.sprites.frontDefault
        )
    }

}
