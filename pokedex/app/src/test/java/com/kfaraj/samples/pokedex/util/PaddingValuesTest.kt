package com.kfaraj.samples.pokedex.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.junit.Assert.assertEquals
import org.junit.Test

class PaddingValuesTest {

    @Test
    fun plusPaddingValues() {
        val innerPadding = PaddingValues(24.dp)
        val contentPadding = PaddingValues(8.dp)
        val resultPadding = innerPadding + contentPadding
        assertEquals(32.dp, resultPadding.calculateLeftPadding(LayoutDirection.Ltr))
        assertEquals(32.dp, resultPadding.calculateTopPadding())
        assertEquals(32.dp, resultPadding.calculateRightPadding(LayoutDirection.Ltr))
        assertEquals(32.dp, resultPadding.calculateBottomPadding())
    }

}
