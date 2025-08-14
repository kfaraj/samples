package com.kfaraj.samples.pokedex.shared.di

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.kfaraj.samples.pokedex.shared.data.local.ApplicationDatabase
import com.kfaraj.samples.pokedex.shared.data.local.PokemonsLocalDataSource

/**
 * Provides instances for the database.
 */
object NativeDatabaseFactory {

    /**
     * Creates the [SqlDriver] instance.
     */
    fun createSqlDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = ApplicationDatabase.Schema.synchronous(),
            name = "pokedex.db"
        )
    }

    /**
     * Creates the [PokemonsLocalDataSource] instance.
     */
    fun createPokemonsLocalDataSource(): PokemonsLocalDataSource {
        val sqlDriver = createSqlDriver()
        return DatabaseFactory.createPokemonsLocalDataSource(
            sqlDriver
        )
    }

}
