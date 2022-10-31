package com.kfaraj.samples.pokedex.domain

import java.util.*
import javax.inject.Inject

/**
 * Formats the ID of the Pokémon.
 */
class FormatIdUseCase @Inject constructor() {

    operator fun invoke(id: Int): String {
        return "#%03d".format(Locale.getDefault(), id)
    }

}
