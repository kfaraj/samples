package com.kfaraj.samples.pokedex.shared

import kotlin.test.Test
import kotlin.test.assertEquals

class PlatformTest {

    @Test
    fun platform_android() {
        val platform = platform()
        assertEquals("Android", platform)
    }

}
