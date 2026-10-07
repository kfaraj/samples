package com.kfaraj.samples.pokedex.feature.pokemon

import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.navigation3.runtime.EntryProviderScope
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
    onItemClick: (itemId: Int?) -> Unit,
    onNavigateUp: () -> Unit
) {
    entry<PokemonListKey> {
        with(sharedTransitionScope) {
            PokemonListScreen(
                animatedVisibilityScope = LocalNavAnimatedContentScope.current,
                title = title,
                viewModel = koinViewModel(),
                onItemClick = { item ->
                    onItemClick(item?.id)
                }
            )
        }
    }
    entry<PokemonDetailKey>(
        metadata = metadata {
            val transition = fadeIn(
                animationSpec = tween(700)
            ) togetherWith fadeOut(
                animationSpec = tween(700)
            )
            put(NavDisplay.TransitionKey) {
                transition
            }
            put(NavDisplay.PopTransitionKey) {
                transition
            }
            put(NavDisplay.PredictivePopTransitionKey) {
                transition
            }
        }
    ) { key ->
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
