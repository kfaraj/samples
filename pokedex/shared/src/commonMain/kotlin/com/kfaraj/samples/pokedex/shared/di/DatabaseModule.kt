package com.kfaraj.samples.pokedex.shared.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.kfaraj.samples.pokedex.shared.data.local.ApplicationDatabase
import com.kfaraj.samples.pokedex.shared.data.local.PokemonDao
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.scope.Scope

/**
 * Provides bindings for the database.
 */
@Module
@Configuration
object DatabaseModule {

    /**
     * Provides the [ApplicationDatabase] instance.
     */
    @Single
    fun provideApplicationDatabase(
        scope: Scope,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): ApplicationDatabase {
        return roomDatabaseBuilder<ApplicationDatabase>(scope, "pokedex.db")
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(ioDispatcher)
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
expect inline fun <reified T : RoomDatabase> roomDatabaseBuilder(
    scope: Scope,
    name: String
): RoomDatabase.Builder<T>
