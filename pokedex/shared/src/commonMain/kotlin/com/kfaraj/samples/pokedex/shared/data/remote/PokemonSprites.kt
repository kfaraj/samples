package com.kfaraj.samples.pokedex.shared.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Contains the Pokémon sprites.
 */
@Serializable
data class PokemonSprites(
    @SerialName("front_default") val frontDefault: String
)
