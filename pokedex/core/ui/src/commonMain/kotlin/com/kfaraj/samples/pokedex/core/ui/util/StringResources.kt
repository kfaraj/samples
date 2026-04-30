package com.kfaraj.samples.pokedex.core.ui.util

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

/**
 * Retrieves a formatted string using the specified string resource and arguments.
 *
 * @param resource The string resource to be used.
 * @param formatArgs The arguments to be inserted into the formatted string.
 * @return The formatted string resource.
 *
 * @throws IllegalArgumentException If the provided ID is not found in the resource file.
 */
@Composable
public fun stringResource(resource: StringResource, vararg formatArgs: Any): String {
    val args = formatArgs.map { it.toString() }
    val str = stringResource(resource)
    return str.replaceWithArgs(args)
}
