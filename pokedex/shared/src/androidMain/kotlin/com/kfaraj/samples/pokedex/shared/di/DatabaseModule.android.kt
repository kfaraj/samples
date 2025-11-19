package com.kfaraj.samples.pokedex.shared.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.core.scope.Scope

actual inline fun <reified T : RoomDatabase> roomDatabaseBuilder(
    scope: Scope,
    name: String
): RoomDatabase.Builder<T> {
    val applicationContext = scope.get<Context>()
    return Room.databaseBuilder(
        context = applicationContext,
        name = applicationContext.getDatabasePath(name).absolutePath
    )
}
