package com.kfaraj.samples.pokedex.data.remote

import com.google.gson.annotations.SerializedName

/**
 * Contains the paginated list of named API resources.
 */
data class NamedApiResourceList(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<NamedApiResource>
)
