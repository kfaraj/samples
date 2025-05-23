package com.kfaraj.samples.pokedex.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Contains the paginated list of named API resources.
 */
@Serializable
data class NamedApiResourceList(
    @SerialName("count") val count: Int,
    @SerialName("next") val next: String?,
    @SerialName("previous") val previous: String?,
    @SerialName("results") val results: List<NamedApiResource>
)
