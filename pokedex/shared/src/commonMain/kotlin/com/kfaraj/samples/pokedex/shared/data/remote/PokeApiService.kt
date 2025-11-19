package com.kfaraj.samples.pokedex.shared.data.remote

/**
 * Exposes Pokémon data from an API service.
 */
interface PokeApiService {

    /**
     * Returns the paginated list of Pokémon species API resources.
     */
    suspend fun getPokemonSpecies(
        limit: Int,
        offset: Int
    ): NamedApiResourceList

}
