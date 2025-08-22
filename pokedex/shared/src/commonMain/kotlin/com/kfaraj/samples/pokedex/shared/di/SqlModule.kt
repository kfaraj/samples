package com.kfaraj.samples.pokedex.shared.di

import app.cash.sqldelight.db.SqlDriver
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.scope.Scope

/**
 * Provides bindings for SQL.
 */
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
@Module
expect object SqlModule {

    /**
     * Provides the [SqlDriver] instance.
     */
    @Single
    fun provideSqlDriver(
        scope: Scope
    ): SqlDriver

}
