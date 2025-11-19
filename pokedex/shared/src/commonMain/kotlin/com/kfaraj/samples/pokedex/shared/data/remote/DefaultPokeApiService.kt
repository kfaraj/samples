package com.kfaraj.samples.pokedex.shared.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class DefaultPokeApiService(
    private val httpClient: HttpClient
) : PokeApiService {

    override suspend fun getPokemonSpecies(
        limit: Int,
        offset: Int
    ): NamedApiResourceList {
        return httpClient.get("pokemon-species/") {
            parameter("limit", limit)
            parameter("offset", offset)
        }.body()
    }

}
