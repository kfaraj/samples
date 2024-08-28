package com.kfaraj.samples.pokedex

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController.OnDestinationChangedListener
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kfaraj.samples.pokedex.domain.FormatIdUseCase
import com.kfaraj.samples.pokedex.domain.FormatNameUseCase
import com.kfaraj.samples.pokedex.navigation.PokedexRoute
import com.kfaraj.samples.pokedex.navigation.navigateToPokemon
import com.kfaraj.samples.pokedex.navigation.pokedexDestination
import com.kfaraj.samples.pokedex.navigation.pokemonDestination
import com.kfaraj.samples.pokedex.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Demonstrates best practices for Modern Android Development.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var formatIdUseCase: FormatIdUseCase

    @Inject
    lateinit var formatNameUseCase: FormatNameUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        // TODO: Add transition.
        // TODO: Handle window insets padding.
        // TODO: Coordinate app bar title.
        setContent {
            AppTheme {
                val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
                val navController = rememberNavController()
                var isTopLevelDestination by remember { mutableStateOf(false) }
                DisposableEffect(navController) {
                    val listener = OnDestinationChangedListener { controller, _, _ ->
                        isTopLevelDestination = controller.previousBackStackEntry == null
                    }
                    navController.addOnDestinationChangedListener(listener)
                    onDispose {
                        navController.removeOnDestinationChangedListener(listener)
                    }
                }
                Scaffold(
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = {
                        TopAppBar(
                            title = {
                                if (isTopLevelDestination) {
                                    Text(
                                        text = stringResource(R.string.app_name)
                                    )
                                }
                            },
                            scrollBehavior = scrollBehavior,
                            navigationIcon = {
                                if (!isTopLevelDestination) {
                                    IconButton(
                                        onClick = {
                                            navController.navigateUp()
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = stringResource(R.string.navigate_up)
                                        )
                                    }
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = PokedexRoute,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        pokedexDestination(
                            formatIdUseCase = formatIdUseCase,
                            formatNameUseCase = formatNameUseCase,
                            onClick = { item ->
                                item?.id?.let {
                                    navController.navigateToPokemon(it)
                                }
                            }
                        )
                        pokemonDestination(
                            formatIdUseCase = formatIdUseCase,
                            formatNameUseCase = formatNameUseCase
                        )
                    }
                }
            }
        }
    }

}
