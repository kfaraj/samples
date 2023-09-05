package com.kfaraj.samples.pokedex.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Contains the Pokémon entity.
 */
@Entity(tableName = "pokemons")
data class PokemonEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String
)
