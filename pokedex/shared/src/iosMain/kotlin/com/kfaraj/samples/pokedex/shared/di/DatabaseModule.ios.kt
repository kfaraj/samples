package com.kfaraj.samples.pokedex.shared.di

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.koin.core.scope.Scope

actual fun sqlDriver(
    schema: SqlSchema<QueryResult.Value<Unit>>,
    scope: Scope,
    name: String
): SqlDriver {
    return NativeSqliteDriver(
        schema = schema,
        name = name
    )
}
