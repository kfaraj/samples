package com.kfaraj.samples.pokedex.data.remote

import org.koin.core.annotation.Factory

/**
 * Exposes Pokémon data from a remote data source.
 */
@Factory
class PokemonsRemoteDataSource(
    private val pokeApiService: PokeApiService
) {

    /**
     * Returns the paginated list of Pokémon species API resources.
     */
    suspend fun getPokemonSpecies(
        limit: Int,
        offset: Int
    ): NamedApiResourceList {
        return pokeApiService.getPokemonSpecies(limit, offset)
    }

}
