package com.kfaraj.samples.pokedex.shared

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.ui.window.ComposeUIViewController
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.defaultPopTransitionSpec
import androidx.savedstate.serialization.SavedStateConfiguration
import com.kfaraj.samples.pokedex.core.ui.theme.AppTheme
import com.kfaraj.samples.pokedex.feature.pokemon.PokemonDetailKey
import com.kfaraj.samples.pokedex.feature.pokemon.PokemonListKey
import com.kfaraj.samples.pokedex.feature.pokemon.pokemonDetailEntryBuilder
import com.kfaraj.samples.pokedex.feature.pokemon.pokemonListEntryBuilder
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import platform.UIKit.UIViewController

/**
 * Contains the [NavDisplay].
 */
public fun MainViewController(
    title: String
): UIViewController = ComposeUIViewController {
    AppTheme {
        SharedTransitionLayout {
            val configuration = SavedStateConfiguration {
                serializersModule = SerializersModule {
                    polymorphic(NavKey::class) {
                        subclass(PokemonListKey::class, PokemonListKey.serializer())
                        subclass(PokemonDetailKey::class, PokemonDetailKey.serializer())
                    }
                }
            }
            val backStack = rememberNavBackStack(configuration, PokemonListKey)
            val popTransitionSpec = defaultPopTransitionSpec<NavKey>()
            NavDisplay(
                backStack = backStack,
                entryDecorators = listOf(
                    rememberSaveableStateHolderNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator()
                ),
                predictivePopTransitionSpec = {
                    popTransitionSpec()
                },
                entryProvider = entryProvider {
                    pokemonListEntryBuilder(
                        sharedTransitionScope = this@SharedTransitionLayout,
                        title = title,
                        onItemClick = { itemId ->
                            itemId?.let {
                                val key = PokemonDetailKey(it)
                                backStack.add(key)
                            }
                        }
                    )
                    pokemonDetailEntryBuilder(
                        sharedTransitionScope = this@SharedTransitionLayout,
                        onNavigateUp = {
                            backStack.removeLastOrNull()
                        }
                    )
                }
            )
        }
    }
}
