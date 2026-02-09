package com.kfaraj.samples.pokedex.shared.data.remote

/**
 * Exposes Pokémon data from a remote data source.
 */
interface PokemonRemoteDataSource {

    /**
     * Returns the paginated list of Pokémon species API resources.
     */
    suspend fun getPokemonSpecies(
        limit: Int,
        offset: Int
    ): NamedApiResourceList

}
