package com.kfaraj.samples.pokedex.ui

import android.content.res.Resources
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.Navigator
import androidx.navigation.fragment.fragment
import com.kfaraj.samples.pokedex.R
import kotlinx.serialization.Serializable

/**
 * Identifies the Pokémon destination.
 */
@Serializable
data class PokemonRoute(
    val id: Int
)

/**
 * Adds the Pokémon destination.
 */
fun NavGraphBuilder.pokemonDestination(
    resources: Resources
) {
    fragment<PokemonFragment, PokemonRoute> {
        label = resources.getString(R.string.empty)
    }
}

/**
 * Navigates to the Pokémon destination.
 */
fun NavController.navigateToPokemonDestination(
    id: Int,
    navigatorExtras: Navigator.Extras
) {
    val route = PokemonRoute(id)
    navigate(route, null, navigatorExtras)
}
