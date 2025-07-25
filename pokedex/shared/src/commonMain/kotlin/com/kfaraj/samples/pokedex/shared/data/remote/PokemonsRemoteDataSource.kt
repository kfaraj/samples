package com.kfaraj.samples.pokedex.shared.data.remote

/**
 * Exposes Pokémon data from a remote data source.
 */
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
