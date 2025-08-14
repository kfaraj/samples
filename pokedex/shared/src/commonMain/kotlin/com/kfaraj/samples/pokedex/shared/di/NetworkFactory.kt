package com.kfaraj.samples.pokedex.shared.di

import com.kfaraj.samples.pokedex.shared.data.remote.DefaultPokeApiService
import com.kfaraj.samples.pokedex.shared.data.remote.PokeApiService
import com.kfaraj.samples.pokedex.shared.data.remote.PokemonsRemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Creates instances for the network.
 */
object NetworkFactory {

    private val json = Json {
        encodeDefaults = true
        explicitNulls = false
        ignoreUnknownKeys = true
    }

    private var httpClient: HttpClient? = null

    /**
     * Creates the [HttpClient] instance.
     */
    fun createHttpClient(): HttpClient {
        return httpClient ?: HttpClient {
            defaultRequest {
                url("https://pokeapi.co/api/v2/")
            }
            install(ContentNegotiation) {
                json(json)
            }
            expectSuccess = true
        }.also {
            httpClient = it
        }
    }

    /**
     * Creates the [PokeApiService] instance.
     */
    fun createPokeApiService(
        httpClient: HttpClient
    ): PokeApiService {
        return DefaultPokeApiService(
            httpClient
        )
    }

    /**
     * Creates the [PokemonsRemoteDataSource] instance.
     */
    fun createPokemonsRemoteDataSource(): PokemonsRemoteDataSource {
        val httpClient = createHttpClient()
        val pokeApiService = createPokeApiService(
            httpClient
        )
        return PokemonsRemoteDataSource(
            pokeApiService
        )
    }

}
