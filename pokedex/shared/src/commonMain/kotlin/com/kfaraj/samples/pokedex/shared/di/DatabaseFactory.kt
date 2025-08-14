package com.kfaraj.samples.pokedex.shared.di

import app.cash.sqldelight.db.SqlDriver
import com.kfaraj.samples.pokedex.shared.data.local.ApplicationDatabase
import com.kfaraj.samples.pokedex.shared.data.local.PokemonEntityQueries
import com.kfaraj.samples.pokedex.shared.data.local.PokemonsLocalDataSource

/**
 * Creates instances for the database.
 */
object DatabaseFactory {

    /**
     * Creates the [ApplicationDatabase] instance.
     */
    fun createApplicationDatabase(
        sqlDriver: SqlDriver
    ): ApplicationDatabase {
        return ApplicationDatabase(sqlDriver)
    }

    /**
     * Creates the [PokemonEntityQueries] instance.
     */
    fun createPokemonQueries(
        applicationDatabase: ApplicationDatabase
    ): PokemonEntityQueries {
        return applicationDatabase.pokemonEntityQueries
    }

    /**
     * Creates the [PokemonsLocalDataSource] instance.
     */
    fun createPokemonsLocalDataSource(
        sqlDriver: SqlDriver
    ): PokemonsLocalDataSource {
        val applicationDatabase = createApplicationDatabase(
            sqlDriver
        )
        val pokemonQueries = createPokemonQueries(
            applicationDatabase
        )
        return PokemonsLocalDataSource(
            pokemonQueries
        )
    }

}
