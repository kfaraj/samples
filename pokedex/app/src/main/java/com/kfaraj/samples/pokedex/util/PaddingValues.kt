package com.kfaraj.samples.pokedex.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

/**
 * Adds two [PaddingValues] together.
 */
operator fun PaddingValues.plus(other: PaddingValues): PaddingValues = object : PaddingValues {
    override fun calculateLeftPadding(layoutDirection: LayoutDirection): Dp =
        this@plus.calculateLeftPadding(layoutDirection) +
                other.calculateLeftPadding(layoutDirection)

    override fun calculateTopPadding(): Dp =
        this@plus.calculateTopPadding() +
                other.calculateTopPadding()

    override fun calculateRightPadding(layoutDirection: LayoutDirection): Dp =
        this@plus.calculateRightPadding(layoutDirection) +
                other.calculateRightPadding(layoutDirection)

    override fun calculateBottomPadding(): Dp =
        this@plus.calculateBottomPadding() +
                other.calculateBottomPadding()
}
