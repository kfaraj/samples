package com.kfaraj.samples.pokedex.data.remote

import org.koin.core.annotation.Factory

/**
 * Provides a default implementation of [PokemonRemoteDataSource].
 */
@Factory
class DefaultPokemonRemoteDataSource(
    private val pokeApiService: PokeApiService
) : PokemonRemoteDataSource {

    override suspend fun getPokemonSpecies(
        limit: Int,
        offset: Int
    ): NamedApiResourceList {
        return pokeApiService.getPokemonSpecies(limit, offset)
    }

}
