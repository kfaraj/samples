package com.kfaraj.samples.pokedex.domain

import java.util.*
import javax.inject.Inject

/**
 * Formats the name of the Pokémon.
 */
class FormatNameUseCase @Inject constructor() {

    operator fun invoke(name: String): String {
        return name.replaceFirstChar { it.titlecase(Locale.getDefault()) }
    }

}
