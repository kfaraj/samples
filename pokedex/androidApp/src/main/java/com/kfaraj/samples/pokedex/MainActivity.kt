package com.kfaraj.samples.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.SharedTransitionLayout
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kfaraj.samples.pokedex.ui.PokedexRoute
import com.kfaraj.samples.pokedex.ui.navigateToPokemonDestination
import com.kfaraj.samples.pokedex.ui.pokedexDestination
import com.kfaraj.samples.pokedex.ui.pokemonDestination
import com.kfaraj.samples.pokedex.ui.theme.AppTheme

/**
 * Demonstrates best practices for Modern Android Development.
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
                        startDestination = PokedexRoute
                    ) {
                        pokedexDestination(
                            sharedTransitionScope = this@SharedTransitionLayout,
                            onItemClick = { item ->
                                item?.id?.let {
                                    navController.navigateToPokemonDestination(it)
                                }
                            }
                        )
                        pokemonDestination(
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
