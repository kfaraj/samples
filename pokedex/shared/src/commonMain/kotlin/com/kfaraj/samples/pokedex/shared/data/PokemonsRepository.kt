package com.kfaraj.samples.pokedex.shared.data

import com.kfaraj.samples.pokedex.shared.data.remote.NamedApiResource
import com.kfaraj.samples.pokedex.shared.data.remote.Pokemon.sprites
import com.kfaraj.samples.pokedex.shared.data.remote.PokemonSpecies.id
import com.kfaraj.samples.pokedex.shared.data.remote.PokemonSpecies.names
import com.kfaraj.samples.pokedex.shared.data.remote.PokemonSpecies.varieties
import com.kfaraj.samples.pokedex.shared.data.remote.PokemonsRemoteDataSource
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines

/**
 * Exposes Pokémon data.
 */
class PokemonsRepository(
    private val pokemonsRemoteDataSource: PokemonsRemoteDataSource
) {

    /**
     * Returns Pokémon data for the given [id].
     */
    @NativeCoroutines
    suspend fun get(id: Int): Pokemon {
        return pokemonsRemoteDataSource.getPokemonSpecies(1, id - 1).results.map { pokemon ->
            pokemon.toPokemon()
        }.single()
    }

    /**
     * Returns the stream of paged Pokémon data.
     */
    @NativeCoroutines
    suspend fun getPagingDataStream(limit: Int, offset: Int): List<Pokemon> {
        return pokemonsRemoteDataSource.getPokemonSpecies(limit, offset).results.map { pokemon ->
            pokemon.toPokemon()
        }
    }

    /**
     * Converts the model from the remote data source to the data layer.
     */
    private fun NamedApiResource.toPokemon(): Pokemon {
        return Pokemon(
            id,
            names.first().name,
            varieties.first().pokemon.sprites.frontDefault
        )
    }

}
