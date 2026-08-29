package com.kfaraj.samples.pokedex.data.pokemon.local

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey

/**
 * Contains the Pokémon entity.
 */
@Entity(tableName = "pokemon")
internal data class PokemonEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "sprite") val sprite: String
)
