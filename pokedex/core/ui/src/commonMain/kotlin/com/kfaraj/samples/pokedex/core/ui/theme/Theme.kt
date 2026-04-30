package com.kfaraj.samples.pokedex.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

/**
 * Provides the app theme.
 */
@Composable
public fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDarkTheme) {
        AppDarkColorScheme
    } else {
        AppLightColorScheme
    }
    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
