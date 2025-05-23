package com.kfaraj.samples.pokedex.di

import com.kfaraj.samples.pokedex.data.remote.PokeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import javax.inject.Singleton

/**
 * Provides bindings for [Retrofit].
 */
@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private val json = Json {
        encodeDefaults = true
        explicitNulls = false
        ignoreUnknownKeys = true
    }

    /**
     * Provides the [Retrofit] instance.
     */
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(
                json.asConverterFactory(
                    "application/json; charset=UTF-8".toMediaType()
                )
            )
            .build()
    }

    /**
     * Provides the [PokeApiService] instance.
     */
    @Provides
    fun providePokeApiService(
        retrofit: Retrofit
    ): PokeApiService {
        return retrofit.create()
    }

}
