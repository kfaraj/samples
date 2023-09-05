package com.kfaraj.samples.pokedex.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class FormatIdUseCaseTest {

    @Test
    fun formatIdUseCase() {
        val formatIdUseCase = FormatIdUseCase()
        val result = formatIdUseCase(1)
        assertEquals("#001", result)
    }

}
