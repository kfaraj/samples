package com.kfaraj.samples.pokedex.data.remote

import javax.inject.Inject

/**
 * Exposes Pokémon data from a remote data source.
 */
class PokemonsRemoteDataSource @Inject constructor(
    private val pokeApiService: PokeApiService
) {

    /**
     * Returns the paginated list of Pokémon API resources.
     */
    suspend fun getPokemon(
        limit: Int,
        offset: Int
    ): NamedApiResourceList {
        return pokeApiService.getPokemon(limit, offset)
    }

}
