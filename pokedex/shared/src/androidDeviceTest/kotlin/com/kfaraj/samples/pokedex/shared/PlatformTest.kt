package com.kfaraj.samples.pokedex.shared

import org.junit.Assert.assertEquals
import org.junit.Test

class PlatformTest {

    @Test
    fun platform_android() {
        val platform = platform()
        assertEquals("Android", platform)
    }

}
