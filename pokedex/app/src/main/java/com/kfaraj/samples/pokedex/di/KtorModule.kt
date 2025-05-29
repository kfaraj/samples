package com.kfaraj.samples.pokedex.di

import com.kfaraj.samples.pokedex.data.remote.DefaultPokeApiService
import com.kfaraj.samples.pokedex.data.remote.PokeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

/**
 * Provides bindings for Ktor.
 */
@Module
@InstallIn(SingletonComponent::class)
object KtorModule {

    private val json = Json {
        encodeDefaults = true
        explicitNulls = false
        ignoreUnknownKeys = true
    }

    /**
     * Provides the [HttpClient] instance.
     */
    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient(OkHttp) {
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
    @Provides
    fun providePokeApiService(
        httpClient: HttpClient
    ): PokeApiService {
        return DefaultPokeApiService(
            httpClient
        )
    }

}
