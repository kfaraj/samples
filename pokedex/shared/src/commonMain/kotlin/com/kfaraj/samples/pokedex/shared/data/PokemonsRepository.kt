package com.kfaraj.samples.pokedex.shared.data

import com.kfaraj.samples.pokedex.shared.data.local.PokemonEntity
import com.kfaraj.samples.pokedex.shared.data.local.PokemonsLocalDataSource
import com.kfaraj.samples.pokedex.shared.data.remote.NamedApiResource
import com.kfaraj.samples.pokedex.shared.data.remote.Pokemon.sprites
import com.kfaraj.samples.pokedex.shared.data.remote.PokemonSpecies.id
import com.kfaraj.samples.pokedex.shared.data.remote.PokemonSpecies.names
import com.kfaraj.samples.pokedex.shared.data.remote.PokemonSpecies.varieties
import com.kfaraj.samples.pokedex.shared.data.remote.PokemonsRemoteDataSource
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import io.ktor.client.plugins.ResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.io.IOException
import org.koin.core.annotation.Single

/**
 * Exposes Pokémon data.
 */
@Single
class PokemonsRepository(
    private val pokemonsRemoteDataSource: PokemonsRemoteDataSource,
    private val pokemonsLocalDataSource: PokemonsLocalDataSource
) {

    /**
     * Returns Pokémon data for the given [id].
     */
    @NativeCoroutines
    suspend fun get(id: Int): Pokemon {
        return pokemonsLocalDataSource.get(id).toPokemon()
    }

    /**
     * Returns the stream of paged Pokémon data.
     */
    fun getPagingDataStream(pageSize: Int): Flow<List<Pokemon>> {
        return pokemonsLocalDataSource.getPagingSource(Int.MAX_VALUE, 0)
            .onEach { pagingData ->
                try {
                    val response = pokemonsRemoteDataSource.getPokemonSpecies(
                        limit = pageSize,
                        offset = pagingData.size
                    )
                    val pokemons = response
                        .results
                        .map { result ->
                            result.toPokemonEntity()
                        }
                    pokemonsLocalDataSource.upsertAll(pokemons)
                } catch (e: IOException) {
                    e.printStackTrace()
                } catch (e: ResponseException) {
                    e.printStackTrace()
                }
            }
            .map { pagingData ->
                pagingData.map { pokemonEntity ->
                    pokemonEntity.toPokemon()
                }
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
