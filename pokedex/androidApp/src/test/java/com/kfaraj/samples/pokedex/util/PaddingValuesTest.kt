package com.kfaraj.samples.pokedex.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.junit.Assert.assertEquals
import org.junit.Test

class PaddingValuesTest {

    @Test
    fun plus() {
        val paddingValues = PaddingValues(24.dp) + PaddingValues(8.dp)
        assertEquals(32.dp, paddingValues.calculateLeftPadding(LayoutDirection.Ltr))
        assertEquals(32.dp, paddingValues.calculateTopPadding())
        assertEquals(32.dp, paddingValues.calculateRightPadding(LayoutDirection.Ltr))
        assertEquals(32.dp, paddingValues.calculateBottomPadding())
    }

}
