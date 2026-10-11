package com.kfaraj.samples.pokedex.feature.pokemon

import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.metadata
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import androidx.navigation3.ui.NavDisplay
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

/**
 * Builds the Pokémon feature entry.
 */
public fun EntryProviderScope<NavKey>.pokemonFeatureEntryBuilder(
    sharedTransitionScope: SharedTransitionScope,
    title: String,
    backStack: NavBackStack<NavKey>
) {
    entry<PokemonListKey> {
        with(sharedTransitionScope) {
            PokemonListScreen(
                animatedVisibilityScope = LocalNavAnimatedContentScope.current,
                title = title,
                viewModel = koinViewModel(),
                onItemClick = { item ->
                    item?.id?.let {
                        val key = PokemonDetailKey(it)
                        backStack.add(key)
                    }
                }
            )
        }
    }
    entry<PokemonDetailKey>(
        metadata = metadata {
            put(NavDisplay.TransitionKey) {
                fadeIn() togetherWith fadeOut()
            }
            put(NavDisplay.PopTransitionKey) {
                fadeIn() togetherWith fadeOut()
            }
            put(NavDisplay.PredictivePopTransitionKey) {
                fadeIn() togetherWith fadeOut()
            }
        }
    ) { key ->
        with(sharedTransitionScope) {
            PokemonDetailScreen(
                animatedVisibilityScope = LocalNavAnimatedContentScope.current,
                viewModel = koinViewModel {
                    parametersOf(key)
                },
                onBackClick = {
                    backStack.removeLastOrNull()
                }
            )
        }
    }
}
