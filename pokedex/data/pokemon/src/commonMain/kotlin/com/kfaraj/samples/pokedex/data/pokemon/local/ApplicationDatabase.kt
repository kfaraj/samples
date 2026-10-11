package com.kfaraj.samples.pokedex.data.pokemon.local

import androidx.room3.ConstructedBy
import androidx.room3.Database
import androidx.room3.RoomDatabase
import androidx.room3.RoomDatabaseConstructor

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
