package com.kfaraj.samples.pokedex.shared.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Contains the named API resource.
 */
@Serializable
data class NamedApiResource(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
)
