package com.kfaraj.samples.pokedex.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Primary40,
    onPrimary = Primary100,
    primaryContainer = Primary90,
    onPrimaryContainer = Primary10,
    inversePrimary = Primary80,
    secondary = Secondary40,
    onSecondary = Secondary100,
    secondaryContainer = Secondary90,
    onSecondaryContainer = Secondary10,
    tertiary = Tertiary40,
    onTertiary = Tertiary100,
    tertiaryContainer = Tertiary90,
    onTertiaryContainer = Tertiary10,
    background = Neutral98,
    onBackground = Neutral10,
    surface = Neutral98,
    onSurface = Neutral10,
    surfaceVariant = NeutralVariant90,
    onSurfaceVariant = NeutralVariant30,
    inverseSurface = Neutral20,
    inverseOnSurface = Neutral95,
    outline = NeutralVariant50,
    outlineVariant = NeutralVariant80,
    surfaceBright = Neutral98,
    surfaceContainer = Neutral94,
    surfaceContainerHigh = Neutral92,
    surfaceContainerHighest = Neutral90,
    surfaceContainerLow = Neutral96,
    surfaceContainerLowest = Neutral100,
    surfaceDim = Neutral87
)

private val DarkColorScheme = darkColorScheme(
    primary = Primary80,
    onPrimary = Primary20,
    primaryContainer = Primary30,
    onPrimaryContainer = Primary90,
    inversePrimary = Primary40,
    secondary = Secondary80,
    onSecondary = Secondary20,
    secondaryContainer = Secondary30,
    onSecondaryContainer = Secondary90,
    tertiary = Tertiary80,
    onTertiary = Tertiary20,
    tertiaryContainer = Tertiary30,
    onTertiaryContainer = Tertiary90,
    background = Neutral6,
    onBackground = Neutral90,
    surface = Neutral6,
    onSurface = Neutral90,
    surfaceVariant = NeutralVariant30,
    onSurfaceVariant = NeutralVariant80,
    inverseSurface = Neutral90,
    inverseOnSurface = Neutral20,
    outline = NeutralVariant60,
    outlineVariant = NeutralVariant30,
    surfaceBright = Neutral24,
    surfaceContainer = Neutral12,
    surfaceContainerHigh = Neutral17,
    surfaceContainerHighest = Neutral22,
    surfaceContainerLow = Neutral10,
    surfaceContainerLowest = Neutral4,
    surfaceDim = Neutral6
)

/**
 * Provides the app theme.
 */
@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDarkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }
    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
