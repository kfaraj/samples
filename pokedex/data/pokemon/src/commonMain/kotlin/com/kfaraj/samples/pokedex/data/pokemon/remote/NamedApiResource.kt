package com.kfaraj.samples.pokedex.data.pokemon.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Contains the named API resource.
 */
@Serializable
internal data class NamedApiResource(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
)
