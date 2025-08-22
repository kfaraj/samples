package com.kfaraj.samples.pokedex.shared.di

import androidx.room.RoomDatabase
import com.kfaraj.samples.pokedex.shared.data.local.ApplicationDatabase
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.scope.Scope

/**
 * Provides bindings for SQL.
 */
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
@Module
expect object DatabaseBuilderModule {

    /**
     * Provides the [ApplicationDatabase] builder instance.
     */
    @Single
    fun provideApplicationDatabaseBuilder(
        scope: Scope
    ): RoomDatabase.Builder<ApplicationDatabase>

}
