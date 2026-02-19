package com.kfaraj.samples.pokedex.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Contains the name.
 */
@Serializable
data class Name(
    @SerialName("name") val name: String,
    @SerialName("language") val language: NamedApiResource
)
