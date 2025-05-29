package com.kfaraj.samples.pokedex.data.remote

/**
 * Exposes Pokémon data from a Ktor service.
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
