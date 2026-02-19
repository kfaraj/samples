package com.kfaraj.samples.pokedex.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Contains the Pokémon species variety.
 */
@Serializable
data class PokemonSpeciesVariety(
    @SerialName("is_default") val isDefault: Boolean,
    @SerialName("pokemon") val pokemon: NamedApiResource
)
