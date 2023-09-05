package com.kfaraj.samples.pokedex.di

import android.content.Context
import androidx.room.Room
import com.kfaraj.samples.pokedex.data.local.ApplicationDatabase
import com.kfaraj.samples.pokedex.data.local.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Provides bindings for [Room].
 */
@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    /**
     * Provides the [ApplicationDatabase] instance.
     */
    @Singleton
    @Provides
    fun provideApplicationDatabase(
        @ApplicationContext applicationContext: Context
    ): ApplicationDatabase {
        return Room.databaseBuilder(
            applicationContext,
            ApplicationDatabase::class.java,
            "pokedex.db"
        ).build()
    }

    /**
     * Provides the [PokemonDao] instance.
     */
    @Provides
    fun providePokemonDao(
        applicationDatabase: ApplicationDatabase
    ): PokemonDao {
        return applicationDatabase.getPokemonDao()
    }

}
