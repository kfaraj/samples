package com.kfaraj.samples.pokedex.shared.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.kfaraj.samples.pokedex.shared.data.local.ApplicationDatabase
import com.kfaraj.samples.pokedex.shared.data.local.PokemonDao
import kotlinx.coroutines.CoroutineDispatcher
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
        builder: RoomDatabase.Builder<ApplicationDatabase>,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): ApplicationDatabase {
        return builder
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
