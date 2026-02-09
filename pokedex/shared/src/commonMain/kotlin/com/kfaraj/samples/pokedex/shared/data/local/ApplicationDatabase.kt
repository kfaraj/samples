package com.kfaraj.samples.pokedex.shared.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor

/**
 * Exposes application data from a database.
 */
@Database(entities = [PokemonEntity::class], version = 1)
@ConstructedBy(ApplicationDatabaseConstructor::class)
abstract class ApplicationDatabase : RoomDatabase() {

    /**
     * Returns the [PokemonDao] instance.
     */
    abstract fun getPokemonDao(): PokemonDao

}

/**
 * Returns the [ApplicationDatabase] instance.
 */
@Suppress("KotlinNoActualForExpect")
expect object ApplicationDatabaseConstructor : RoomDatabaseConstructor<ApplicationDatabase> {
    override fun initialize(): ApplicationDatabase
}
