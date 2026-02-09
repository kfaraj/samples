package com.kfaraj.samples.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.SharedTransitionLayout
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kfaraj.samples.pokedex.ui.PokemonListRoute
import com.kfaraj.samples.pokedex.ui.navigateToPokemonDetailDestination
import com.kfaraj.samples.pokedex.ui.pokemonDetailDestination
import com.kfaraj.samples.pokedex.ui.pokemonListDestination
import com.kfaraj.samples.pokedex.ui.theme.AppTheme

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
                    NavHost(
                        navController = navController,
                        startDestination = PokemonListRoute
                    ) {
                        pokemonListDestination(
                            sharedTransitionScope = this@SharedTransitionLayout,
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
