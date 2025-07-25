package com.kfaraj.samples.pokedex.shared.data

import com.kfaraj.samples.pokedex.shared.data.remote.Pokemon.sprites
import com.kfaraj.samples.pokedex.shared.data.remote.PokemonSpecies.id
import com.kfaraj.samples.pokedex.shared.data.remote.PokemonSpecies.names
import com.kfaraj.samples.pokedex.shared.data.remote.PokemonSpecies.varieties
import com.kfaraj.samples.pokedex.shared.data.remote.PokemonsRemoteDataSource
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines

class PokemonsRepository(
    private val pokemonsRemoteDataSource: PokemonsRemoteDataSource
) {

    @NativeCoroutines
    suspend fun getPokemons(
        limit: Int,
        offset: Int
    ): List<Pokemon> {
        return pokemonsRemoteDataSource.getPokemonSpecies(limit, offset).results.map {
            Pokemon(
                id = it.id,
                name = it.names.first().name,
                sprite = it.varieties.first().pokemon.sprites.frontDefault
            )
        }
    }

}
