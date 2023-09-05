package com.kfaraj.samples.pokedex.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class FormatNameUseCaseTest {

    @Test
    fun formatNameUseCase() {
        val formatNameUseCase = FormatNameUseCase()
        val result = formatNameUseCase("bulbasaur")
        assertEquals("Bulbasaur", result)
    }

}
