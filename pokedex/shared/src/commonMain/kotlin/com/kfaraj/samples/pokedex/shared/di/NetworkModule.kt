package com.kfaraj.samples.pokedex.shared.di

import com.kfaraj.samples.pokedex.shared.data.remote.DefaultPokeApiService
import com.kfaraj.samples.pokedex.shared.data.remote.PokeApiService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

/**
 * Provides bindings for the network.
 */
@Module
@Configuration
object NetworkModule {

    private val json = Json {
        encodeDefaults = true
        explicitNulls = false
        ignoreUnknownKeys = true
    }

    /**
     * Provides the [HttpClient] instance.
     */
    @Single
    fun provideHttpClient(): HttpClient {
        return HttpClient {
            defaultRequest {
                url("https://pokeapi.co/api/v2/")
            }
            install(ContentNegotiation) {
                json(json)
            }
            expectSuccess = true
        }
    }

    /**
     * Provides the [PokeApiService] instance.
     */
    @Factory
    fun providePokeApiService(
        httpClient: HttpClient
    ): PokeApiService {
        return DefaultPokeApiService(
            httpClient
        )
    }

}
