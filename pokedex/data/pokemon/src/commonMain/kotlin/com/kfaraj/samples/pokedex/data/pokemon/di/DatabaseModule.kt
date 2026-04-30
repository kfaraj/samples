package com.kfaraj.samples.pokedex.data.pokemon.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.kfaraj.samples.pokedex.data.pokemon.local.ApplicationDatabase
import com.kfaraj.samples.pokedex.data.pokemon.local.PokemonDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.scope.Scope

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
        scope: Scope
    ): ApplicationDatabase {
        return roomDatabaseBuilder<ApplicationDatabase>(scope, "pokedex.db")
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
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

/**
 * Creates a [RoomDatabase.Builder] for a persistent database.
 */
internal expect inline fun <reified T : RoomDatabase> roomDatabaseBuilder(
    scope: Scope,
    name: String
): RoomDatabase.Builder<T>
