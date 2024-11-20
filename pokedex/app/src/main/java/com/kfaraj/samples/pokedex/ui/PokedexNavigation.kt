package com.kfaraj.samples.pokedex.ui

import android.content.res.Resources
import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import com.kfaraj.samples.pokedex.R
import kotlinx.serialization.Serializable

/**
 * Describes the Pokédex route.
 */
@Serializable
data object PokedexRoute

/**
 * Adds the Pokédex destination.
 */
fun NavGraphBuilder.pokedexDestination(
    resources: Resources
) {
    fragment<PokedexFragment, PokedexRoute> {
        label = resources.getString(R.string.app_name)
    }
}
