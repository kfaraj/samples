package com.kfaraj.samples.pokedex.data

import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.kfaraj.samples.pokedex.data.local.PokemonEntity
import com.kfaraj.samples.pokedex.data.local.PokemonsLocalDataSource
import com.kfaraj.samples.pokedex.data.remote.NamedApiResource
import com.kfaraj.samples.pokedex.data.remote.PokemonsRemoteDataSource
import com.kfaraj.samples.pokedex.data.remote.id
import retrofit2.HttpException
import java.io.IOException

/**
 * Incrementally loads Pokémon data from a remote data source into a local data source.
 */
class PokemonsRemoteMediator(
    private val pokemonsRemoteDataSource: PokemonsRemoteDataSource,
    private val pokemonsLocalDataSource: PokemonsLocalDataSource
) : RemoteMediator<Int, PokemonEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        return try {
            val offset = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(true)
                LoadType.APPEND -> pokemonsLocalDataSource.getCount()
            }
            val response = pokemonsRemoteDataSource.getPokemon(state.config.pageSize, offset)
            val pokemons = response
                .results
                .map { result ->
                    result.toPokemonEntity()
                }
            pokemonsLocalDataSource.insertAll(pokemons)
            MediatorResult.Success(response.next == null)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    override suspend fun initialize(): InitializeAction {
        return if (pokemonsLocalDataSource.getCount() == 0) {
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
            name
        )
    }

}
