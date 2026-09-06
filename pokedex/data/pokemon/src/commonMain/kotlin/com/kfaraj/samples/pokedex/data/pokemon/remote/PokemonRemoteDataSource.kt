package com.kfaraj.samples.pokedex.data.pokemon.remote

/**
 * Exposes Pokémon data from a remote data source.
 */
internal interface PokemonRemoteDataSource {

    /**
     * Returns the paginated list of Pokémon species API resources.
     */
    suspend fun getPokemonSpecies(
        limit: Int,
        offset: Int
    ): NamedApiResourceList

}
