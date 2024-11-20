package com.kfaraj.samples.pokedex

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavHost
import androidx.navigation.createGraph
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.kfaraj.samples.pokedex.ui.PokedexRoute
import com.kfaraj.samples.pokedex.ui.pokedexDestination
import com.kfaraj.samples.pokedex.ui.pokemonDestination
import com.kfaraj.samples.pokedex.util.applyWindowInsetsPadding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Demonstrates best practices for Modern Android Development.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHost
        val navController = navHost.navController
        navController.graph = navController.createGraph(
            startDestination = PokedexRoute
        ) {
            pokedexDestination(resources)
            pokemonDestination(resources)
        }
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        val toolbar = ActivityCompat.requireViewById<MaterialToolbar>(this, R.id.toolbar)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        (toolbar.parent as AppBarLayout).applyWindowInsetsPadding(
            WindowInsetsCompat.Type.systemBars() or
                    WindowInsetsCompat.Type.displayCutout(),
            applyLeft = true,
            applyTop = true,
            applyRight = true
        )
    }

}
