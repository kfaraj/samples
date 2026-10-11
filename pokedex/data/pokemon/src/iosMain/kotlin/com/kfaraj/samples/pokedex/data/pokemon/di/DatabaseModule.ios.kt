package com.kfaraj.samples.pokedex.data.pokemon.di

import androidx.room3.Room
import androidx.room3.RoomDatabase
import org.koin.core.scope.Scope
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

internal actual inline fun <reified T : RoomDatabase> roomDatabaseBuilder(
    scope: Scope,
    name: String
): RoomDatabase.Builder<T> {
    val databaseDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null
    )
    val databasePath = "${databaseDirectory?.path}/$name"
    return Room.databaseBuilder(
        name = databasePath
    )
}
