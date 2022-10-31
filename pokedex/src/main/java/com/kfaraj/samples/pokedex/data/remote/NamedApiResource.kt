package com.kfaraj.samples.pokedex.data.remote

import com.google.gson.annotations.SerializedName

/**
 * Contains the named API resource.
 */
data class NamedApiResource(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

/**
 * The ID of the named API resource.
 */
val NamedApiResource.id: Int
    get() = url.removeSuffix("/").substringAfterLast("/").toInt()
