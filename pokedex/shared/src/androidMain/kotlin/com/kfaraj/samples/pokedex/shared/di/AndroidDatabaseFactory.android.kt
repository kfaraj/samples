package com.kfaraj.samples.pokedex.shared.di

import android.content.Context
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.kfaraj.samples.pokedex.shared.data.local.ApplicationDatabase
import com.kfaraj.samples.pokedex.shared.data.local.PokemonsLocalDataSource

/**
 * Provides instances for the database.
 */
object AndroidDatabaseFactory {

    /**
     * Creates the [SqlDriver] instance.
     */
    fun createSqlDriver(
        applicationContext: Context
    ): SqlDriver {
        return AndroidSqliteDriver(
            schema = ApplicationDatabase.Schema.synchronous(),
            context = applicationContext,
            name = "pokedex.db"
        )
    }

    /**
     * Creates the [PokemonsLocalDataSource] instance.
     */
    fun createPokemonsLocalDataSource(
        applicationContext: Context
    ): PokemonsLocalDataSource {
        val sqlDriver = createSqlDriver(
            applicationContext
        )
        return DatabaseFactory.createPokemonsLocalDataSource(
            sqlDriver
        )
    }

}
