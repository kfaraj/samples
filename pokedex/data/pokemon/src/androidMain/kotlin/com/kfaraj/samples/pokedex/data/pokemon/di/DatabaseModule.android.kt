package com.kfaraj.samples.pokedex.data.pokemon.di

import android.content.Context
import androidx.room3.Room
import androidx.room3.RoomDatabase
import org.koin.core.scope.Scope

internal actual inline fun <reified T : RoomDatabase> roomDatabaseBuilder(
    scope: Scope,
    name: String
): RoomDatabase.Builder<T> {
    val applicationContext = scope.get<Context>()
    val databasePath = applicationContext.getDatabasePath(name).absolutePath
    return Room.databaseBuilder(
        context = applicationContext,
        name = databasePath
    )
}
