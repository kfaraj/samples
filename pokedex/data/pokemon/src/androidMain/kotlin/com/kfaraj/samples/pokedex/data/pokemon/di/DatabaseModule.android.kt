package com.kfaraj.samples.pokedex.data.pokemon.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.core.scope.Scope

internal actual inline fun <reified T : RoomDatabase> roomDatabaseBuilder(
    scope: Scope,
    name: String
): RoomDatabase.Builder<T> {
    val applicationContext = scope.get<Context>()
    return Room.databaseBuilder(
        context = applicationContext,
        name = applicationContext.getDatabasePath(name).absolutePath
    )
}
