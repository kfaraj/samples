package com.kfaraj.samples.pokedex.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class GetSpriteUseCaseTest {

    @Test
    fun getSpriteUseCase() {
        val getSpriteUseCase = GetSpriteUseCase()
        val result = getSpriteUseCase(1)
        assertEquals(
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
            result
        )
    }

}
