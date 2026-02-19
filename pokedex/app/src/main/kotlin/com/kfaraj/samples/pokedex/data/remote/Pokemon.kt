package com.kfaraj.samples.pokedex.data.remote

/**
 * Contains the Pokémon.
 */
object Pokemon {
    val NamedApiResource.id: Int
        get() = url.removeSuffix("/").substringAfterLast("/").toInt()
    val NamedApiResource.sprites: PokemonSprites
        get() = PokemonSprites(
            frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
        )
}
