package com.kfaraj.samples.pokedex.data.remote

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

/**
 * The ID of the named API resource.
 */
val NamedApiResource.id: Int
    get() = url.removeSuffix("/").substringAfterLast("/").toInt()
