package com.kfaraj.samples.pokedex.feature.pokemon

import androidx.compose.animation.SharedTransitionScope
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

/**
 * Builds the Pokémon detail entry.
 */
public fun EntryProviderScope<NavKey>.pokemonDetailEntryBuilder(
    sharedTransitionScope: SharedTransitionScope,
    onNavigateUp: () -> Unit
) {
    entry<PokemonDetailKey> { key ->
        with(sharedTransitionScope) {
            PokemonDetailScreen(
                animatedVisibilityScope = LocalNavAnimatedContentScope.current,
                viewModel = koinViewModel {
                    parametersOf(key)
                },
                onNavigateUp = onNavigateUp
            )
        }
    }
}
