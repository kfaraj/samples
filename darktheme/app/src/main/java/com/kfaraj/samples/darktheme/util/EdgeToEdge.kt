package com.kfaraj.samples.darktheme.util

import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.core.view.ViewCompat
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding

/**
 * Increases margin to avoid visual overlap caused by edge-to-edge.
 */
fun View.applyWindowInsetsMargin(
    typeMask: Int,
    applyLeft: Boolean = false,
    applyTop: Boolean = false,
    applyRight: Boolean = false,
    applyBottom: Boolean = false
) {
    val marginLeft = marginLeft
    val marginTop = marginTop
    val marginRight = marginRight
    val marginBottom = marginBottom
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        val typeInsets = insets.getInsets(typeMask)
        v.updateLayoutParams<MarginLayoutParams> {
            leftMargin = marginLeft + if (applyLeft) typeInsets.left else 0
            topMargin = marginTop + if (applyTop) typeInsets.top else 0
            rightMargin = marginRight + if (applyRight) typeInsets.right else 0
            bottomMargin = marginBottom + if (applyBottom) typeInsets.bottom else 0
        }
        insets
    }
}

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
