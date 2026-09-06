package com.kfaraj.samples.pokedex.core.ui.util

private val SimpleStringFormatRegex = Regex("""%(\d+)\$(0\d+)?[ds]""")

internal fun String.replaceWithArgs(args: List<String>) =
    SimpleStringFormatRegex.replace(this) { matchResult ->
        args[matchResult.groupValues[1].toInt() - 1].let { arg ->
            matchResult.groupValues[2].toIntOrNull()?.let { length ->
                arg.padStart(length, '0')
            } ?: arg
        }
    }
