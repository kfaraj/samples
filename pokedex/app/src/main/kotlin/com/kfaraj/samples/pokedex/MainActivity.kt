package com.kfaraj.samples.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.defaultPopTransitionSpec
import com.kfaraj.samples.pokedex.core.ui.theme.AppTheme
import com.kfaraj.samples.pokedex.feature.pokemon.PokemonDetailKey
import com.kfaraj.samples.pokedex.feature.pokemon.PokemonListKey
import com.kfaraj.samples.pokedex.feature.pokemon.pokemonDetailEntryBuilder
import com.kfaraj.samples.pokedex.feature.pokemon.pokemonListEntryBuilder

/**
 * Contains the [NavDisplay].
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                SharedTransitionLayout {
                    val backStack = rememberNavBackStack(PokemonListKey)
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
                                title = stringResource(R.string.app_name),
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
    }

}
