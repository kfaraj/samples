package com.kfaraj.samples.pokedex.feature.pokemon

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Identifies the Pokémon detail content.
 */
@Serializable
public data class PokemonDetailKey(
    val id: Int
) : NavKey
