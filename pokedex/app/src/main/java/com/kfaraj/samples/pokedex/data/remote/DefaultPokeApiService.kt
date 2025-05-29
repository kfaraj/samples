package com.kfaraj.samples.pokedex.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class DefaultPokeApiService(
    private val httpClient: HttpClient
) : PokeApiService {

    override suspend fun getPokemon(
        limit: Int,
        offset: Int
    ): NamedApiResourceList {
        return httpClient.get("pokemon/") {
            parameter("limit", limit)
            parameter("offset", offset)
        }.body()
    }

}
