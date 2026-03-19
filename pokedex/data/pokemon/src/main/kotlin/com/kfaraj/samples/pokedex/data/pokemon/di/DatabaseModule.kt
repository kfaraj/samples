package com.kfaraj.samples.pokedex.data.pokemon.di

import android.content.Context
import androidx.room.Room
import com.kfaraj.samples.pokedex.data.pokemon.local.ApplicationDatabase
import com.kfaraj.samples.pokedex.data.pokemon.local.PokemonDao
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

/**
 * Provides bindings for the database.
 */
@Module
internal object DatabaseModule {

    /**
     * Provides the [ApplicationDatabase] instance.
     */
    @Single
    fun provideApplicationDatabase(
        applicationContext: Context
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
    @Factory
    fun providePokemonDao(
        applicationDatabase: ApplicationDatabase
    ): PokemonDao {
        return applicationDatabase.getPokemonDao()
    }

}
