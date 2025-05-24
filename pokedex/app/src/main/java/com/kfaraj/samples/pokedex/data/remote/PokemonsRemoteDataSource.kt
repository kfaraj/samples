package com.kfaraj.samples.pokedex.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

/**
 * Exposes Pokémon data from a remote data source.
 */
class PokemonsRemoteDataSource @Inject constructor(
    private val httpClient: HttpClient
) {

    /**
     * Returns the paginated list of Pokémon API resources.
     */
    suspend fun getPokemon(
        limit: Int,
        offset: Int
    ): NamedApiResourceList {
        return httpClient.get("pokemon") {
            parameter("limit", limit)
            parameter("offset", offset)
        }.body()
    }

}
