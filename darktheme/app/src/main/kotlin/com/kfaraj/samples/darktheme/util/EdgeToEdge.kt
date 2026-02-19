package com.kfaraj.samples.darktheme.util

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding

/**
 * Increases padding to avoid visual overlap caused by edge-to-edge.
 */
fun View.applyWindowInsetsPadding(
    typeMask: Int,
    applyLeft: Boolean = false,
    applyTop: Boolean = false,
    applyRight: Boolean = false,
    applyBottom: Boolean = false
) {
    val paddingLeft = paddingLeft
    val paddingTop = paddingTop
    val paddingRight = paddingRight
    val paddingBottom = paddingBottom
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        val typeInsets = insets.getInsets(typeMask)
        v.updatePadding(
            left = paddingLeft + if (applyLeft) typeInsets.left else 0,
            top = paddingTop + if (applyTop) typeInsets.top else 0,
            right = paddingRight + if (applyRight) typeInsets.right else 0,
            bottom = paddingBottom + if (applyBottom) typeInsets.bottom else 0
        )
        insets
    }
}
