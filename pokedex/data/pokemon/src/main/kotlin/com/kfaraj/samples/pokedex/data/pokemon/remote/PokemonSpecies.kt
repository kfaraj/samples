package com.kfaraj.samples.pokedex.data.pokemon.remote

import java.util.Locale

/**
 * Contains the Pokémon species.
 */
internal object PokemonSpecies {
    val NamedApiResource.id: Int
        get() = url.removeSuffix("/").substringAfterLast("/").toInt()
    val NamedApiResource.names: List<Name>
        get() = listOf(
            Name(
                name = name.replaceFirstChar { it.titlecase(Locale.US) },
                language = NamedApiResource(
                    name = "en",
                    url = "https://pokeapi.co/api/v2/language/9/"
                )
            )
        )
    val NamedApiResource.varieties: List<PokemonSpeciesVariety>
        get() = listOf(
            PokemonSpeciesVariety(
                isDefault = true,
                pokemon = NamedApiResource(
                    name = name,
                    url = "https://pokeapi.co/api/v2/pokemon/$id/"
                )
            )
        )
}
