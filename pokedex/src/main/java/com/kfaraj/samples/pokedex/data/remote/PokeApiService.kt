package com.kfaraj.samples.pokedex.data.remote

import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Exposes Pokémon data from a [Retrofit] service.
 */
interface PokeApiService {

    /**
     * Returns the paginated list of Pokémon API resources.
     */
    @GET("pokemon")
    suspend fun getPokemon(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): NamedApiResourceList

}
