package com.kfaraj.samples.pokedex.core.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val Primary10 = Color(0xFF00201C)
private val Primary20 = Color(0xFF003731)
private val Primary30 = Color(0xFF005048)
private val Primary40 = Color(0xFF006A60)
private val Primary80 = Color(0xFF82D5C8)
private val Primary90 = Color(0xFF9EF2E4)
private val Primary100 = Color(0xFFFFFFFF)
private val Secondary10 = Color(0xFF05201C)
private val Secondary20 = Color(0xFF1C3531)
private val Secondary30 = Color(0xFF334B47)
private val Secondary40 = Color(0xFF4A635F)
private val Secondary80 = Color(0xFFB1CCC6)
private val Secondary90 = Color(0xFFCCE8E2)
private val Secondary100 = Color(0xFFFFFFFF)
private val Tertiary10 = Color(0xFF001E31)
private val Tertiary20 = Color(0xFF153349)
private val Tertiary30 = Color(0xFF2D4961)
private val Tertiary40 = Color(0xFF456179)
private val Tertiary80 = Color(0xFFADCAE6)
private val Tertiary90 = Color(0xFFCCE5FF)
private val Tertiary100 = Color(0xFFFFFFFF)
private val Neutral4 = Color(0xFF090F0E)
private val Neutral6 = Color(0xFF0E1513)
private val Neutral10 = Color(0xFF161D1C)
private val Neutral12 = Color(0xFF1A2120)
private val Neutral17 = Color(0xFF252B2A)
private val Neutral20 = Color(0xFF2B3230)
private val Neutral22 = Color(0xFF303635)
private val Neutral24 = Color(0xFF343B39)
private val Neutral87 = Color(0xFFD5DBD9)
private val Neutral90 = Color(0xFFDDE4E1)
private val Neutral92 = Color(0xFFE3EAE7)
private val Neutral94 = Color(0xFFE9EFED)
private val Neutral95 = Color(0xFFECF2EF)
private val Neutral96 = Color(0xFFEFF5F2)
private val Neutral98 = Color(0xFFF4FBF8)
private val Neutral100 = Color(0xFFFFFFFF)
private val NeutralVariant30 = Color(0xFF3F4947)
private val NeutralVariant50 = Color(0xFF6F7977)
private val NeutralVariant60 = Color(0xFF899390)
private val NeutralVariant80 = Color(0xFFBEC9C6)
private val NeutralVariant90 = Color(0xFFDAE5E1)

/**
 * Provides the app light color scheme.
 */
internal val AppLightColorScheme = lightColorScheme(
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

/**
 * Provides the app dark color scheme.
 */
internal val AppDarkColorScheme = darkColorScheme(
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
