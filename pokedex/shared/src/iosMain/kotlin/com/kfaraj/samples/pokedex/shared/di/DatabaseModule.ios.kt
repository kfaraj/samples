package com.kfaraj.samples.pokedex.shared.di

import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.core.scope.Scope
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual inline fun <reified T : RoomDatabase> roomDatabaseBuilder(
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
    return Room.databaseBuilder(
        name = "${databaseDirectory?.path}/$name"
    )
}
