package com.kfaraj.samples.pokedex.shared.data.remote

/**
 * Contains the Pokémon species.
 */
object PokemonSpecies {
    val NamedApiResource.id: Int
        get() = url.removeSuffix("/").substringAfterLast("/").toInt()
    val NamedApiResource.names: List<Name>
        get() = listOf(
            Name(
                name = name.replaceFirstChar { it.titlecase() },
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
