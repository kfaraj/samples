package com.kfaraj.samples.pokedex.data.pokemon.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Contains the Pokémon sprites.
 */
@Serializable
internal data class PokemonSprites(
    @SerialName("front_default") val frontDefault: String
)
