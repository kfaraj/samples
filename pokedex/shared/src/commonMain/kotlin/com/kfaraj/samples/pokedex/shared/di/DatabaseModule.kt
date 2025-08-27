package com.kfaraj.samples.pokedex.shared.di

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import com.kfaraj.samples.pokedex.shared.data.local.ApplicationDatabase
import com.kfaraj.samples.pokedex.shared.data.local.PokemonEntityQueries
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.scope.Scope

/**
 * Provides bindings for the database.
 */
@Module
class DatabaseModule {

    /**
     * Provides the [ApplicationDatabase] instance.
     */
    @Single
    fun provideApplicationDatabase(
        scope: Scope
    ): ApplicationDatabase {
        val sqlDriver = sqlDriver(
            ApplicationDatabase.Schema.synchronous(),
            scope,
            "pokedex.db"
        )
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

expect fun sqlDriver(
    schema: SqlSchema<QueryResult.Value<Unit>>,
    scope: Scope,
    name: String
): SqlDriver
