package com.kfaraj.samples.pokedex.shared.di

import android.content.Context
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.koin.core.scope.Scope

actual fun sqlDriver(
    schema: SqlSchema<QueryResult.Value<Unit>>,
    scope: Scope,
    name: String
): SqlDriver {
    val applicationContext: Context = scope.get()
    return AndroidSqliteDriver(
        schema = schema,
        context = applicationContext,
        name = name
    )
}
