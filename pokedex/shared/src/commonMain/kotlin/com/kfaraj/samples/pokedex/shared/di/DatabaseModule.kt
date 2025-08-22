package com.kfaraj.samples.pokedex.shared.di

import app.cash.sqldelight.db.SqlDriver
import com.kfaraj.samples.pokedex.shared.data.local.ApplicationDatabase
import com.kfaraj.samples.pokedex.shared.data.local.PokemonEntityQueries
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

/**
 * Provides bindings for the database.
 */
@Module
object DatabaseModule {

    /**
     * Provides the [ApplicationDatabase] instance.
     */
    @Single
    fun provideApplicationDatabase(
        sqlDriver: SqlDriver
    ): ApplicationDatabase {
        return ApplicationDatabase(
            sqlDriver
        )
    }

    /**
     * Provides the [PokemonEntityQueries] instance.
     */
    @Factory
    fun providePokemonQueries(
        applicationDatabase: ApplicationDatabase
    ): PokemonEntityQueries {
        return applicationDatabase.pokemonEntityQueries
    }

}
