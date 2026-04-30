package com.kfaraj.samples.pokedex.data.pokemon.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor

/**
 * Exposes application data from a database.
 */
@Database(entities = [PokemonEntity::class], version = 1)
@ConstructedBy(ApplicationDatabaseConstructor::class)
internal abstract class ApplicationDatabase : RoomDatabase() {

    /**
     * Returns the [PokemonDao] instance.
     */
    abstract fun getPokemonDao(): PokemonDao

}

/**
 * Creates the [ApplicationDatabase] instance.
 */
@Suppress("KotlinNoActualForExpect")
internal expect object ApplicationDatabaseConstructor :
    RoomDatabaseConstructor<ApplicationDatabase> {

    override fun initialize(): ApplicationDatabase

}
