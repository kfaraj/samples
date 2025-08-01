package com.kfaraj.samples.pokedex.di

import com.kfaraj.samples.pokedex.shared.data.remote.PokeApiService
import com.kfaraj.samples.pokedex.shared.data.remote.PokemonsRemoteDataSource
import com.kfaraj.samples.pokedex.shared.di.NetworkFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

/**
 * Provides bindings for the network.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides the [HttpClient] instance.
     */
    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return NetworkFactory.createHttpClient()
    }

    /**
     * Provides the [PokeApiService] instance.
     */
    @Provides
    fun providePokeApiService(
        httpClient: HttpClient
    ): PokeApiService {
        return NetworkFactory.createPokeApiService(
            httpClient
        )
    }

    /**
     * Provides the [PokemonsRemoteDataSource] instance.
     */
    @Provides
    fun providePokemonsRemoteDataSource(
        pokeApiService: PokeApiService
    ): PokemonsRemoteDataSource {
        return NetworkFactory.createPokemonsRemoteDataSource(
            pokeApiService
        )
    }

}
