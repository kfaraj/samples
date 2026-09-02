package com.kfaraj.samples.pokedex.feature.pokemon

import androidx.compose.animation.SharedTransitionScope
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import org.koin.compose.viewmodel.koinViewModel

/**
 * Builds the Pokémon list entry.
 */
public fun EntryProviderScope<NavKey>.pokemonListEntryBuilder(
    sharedTransitionScope: SharedTransitionScope,
    title: String,
    onItemClick: (itemId: Int?) -> Unit
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
}
