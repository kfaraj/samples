package com.kfaraj.samples.pokedex.domain

import javax.inject.Inject

/**
 * Returns the depiction of the Pokémon in battle.
 */
class GetSpriteUseCase @Inject constructor() {

    operator fun invoke(id: Int): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    }

}
