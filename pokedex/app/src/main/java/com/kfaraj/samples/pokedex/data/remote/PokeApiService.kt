package com.kfaraj.samples.pokedex.data.remote

/**
 * Exposes Pokémon data from an API service.
 */
interface PokeApiService {

    /**
     * Returns the paginated list of Pokémon API resources.
     */
    suspend fun getPokemon(
        limit: Int,
        offset: Int
    ): NamedApiResourceList

}
