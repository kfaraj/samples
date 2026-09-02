package com.kfaraj.samples.pokedex.data.pokemon.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Contains the name.
 */
@Serializable
internal data class Name(
    @SerialName("name") val name: String,
    @SerialName("language") val language: NamedApiResource
)
