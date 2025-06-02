package com.kfaraj.samples.pokedex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Exposes application data from a database.
 */
@Database(entities = [PokemonEntity::class], version = 1)
abstract class ApplicationDatabase : RoomDatabase() {

    /**
     * Returns the [PokemonDao] instance.
     */
    abstract fun getPokemonDao(): PokemonDao

}
