package com.kfaraj.samples.pokedex.shared.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor

/**
 * Exposes application data from a database.
 */
@ConstructedBy(ApplicationDatabaseConstructor::class)
@Database(entities = [PokemonEntity::class], version = 1)
abstract class ApplicationDatabase : RoomDatabase() {

    /**
     * Returns the [PokemonDao] instance.
     */
    abstract fun getPokemonDao(): PokemonDao

}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING", "KotlinNoActualForExpect")
expect object ApplicationDatabaseConstructor : RoomDatabaseConstructor<ApplicationDatabase> {
    override fun initialize(): ApplicationDatabase
}
