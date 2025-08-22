package com.kfaraj.samples.pokedex.shared.di

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.kfaraj.samples.pokedex.shared.data.local.ApplicationDatabase
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.scope.Scope

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
@Module
actual object SqlModule {

    @Single
    actual fun provideSqlDriver(
        scope: Scope
    ): SqlDriver {
        return NativeSqliteDriver(
            schema = ApplicationDatabase.Schema.synchronous(),
            name = "pokedex.db"
        )
    }

}
