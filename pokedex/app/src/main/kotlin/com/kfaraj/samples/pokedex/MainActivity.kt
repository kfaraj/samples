package com.kfaraj.samples.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.SharedTransitionLayout
import androidx.navigation.compose.DefaultNavTransitions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kfaraj.samples.pokedex.core.ui.theme.AppTheme
import com.kfaraj.samples.pokedex.feature.pokemon.PokemonListRoute
import com.kfaraj.samples.pokedex.feature.pokemon.navigateToPokemonDetailDestination
import com.kfaraj.samples.pokedex.feature.pokemon.pokemonDetailDestination
import com.kfaraj.samples.pokedex.feature.pokemon.pokemonListDestination

/**
 * Contains the [NavHost].
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                SharedTransitionLayout {
                    val navController = rememberNavController()
                    val enterTransition =
                        DefaultNavTransitions.enterTransition
                    val exitTransition =
                        DefaultNavTransitions.exitTransition
                    val popEnterTransition =
                        DefaultNavTransitions.popEnterTransition(enterTransition)
                    val popExitTransition =
                        DefaultNavTransitions.popExitTransition(exitTransition)
                    NavHost(
                        navController = navController,
                        startDestination = PokemonListRoute,
                        predictivePopEnterTransition = {
                            popEnterTransition()
                        },
                        predictivePopExitTransition = {
                            popExitTransition()
                        }
                    ) {
                        pokemonListDestination(
                            sharedTransitionScope = this@SharedTransitionLayout,
                            title = getString(R.string.app_name),
                            onItemClick = { itemId ->
                                itemId?.let {
                                    navController.navigateToPokemonDetailDestination(it)
                                }
                            }
                        )
                        pokemonDetailDestination(
                            sharedTransitionScope = this@SharedTransitionLayout,
                            onNavigateUp = {
                                navController.navigateUp()
                            }
                        )
                    }
                }
            }
        }
    }

}
