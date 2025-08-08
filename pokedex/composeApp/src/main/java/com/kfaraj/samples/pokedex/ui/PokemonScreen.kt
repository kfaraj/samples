package com.kfaraj.samples.pokedex.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.kfaraj.samples.pokedex.R
import com.kfaraj.samples.pokedex.shared.ui.PokemonUiState
import com.kfaraj.samples.pokedex.ui.theme.AppTheme

/**
 * Displays the Pokémon UI state on the screen.
 */
@Composable
fun SharedTransitionScope.PokemonScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: PokemonViewModel,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    PokemonScreen(
        animatedVisibilityScope = animatedVisibilityScope,
        uiState = uiState.value,
        onNavigateUp = onNavigateUp,
        modifier = modifier
    )
}

/**
 * Displays the Pokémon UI state on the screen.
 */
@Composable
private fun SharedTransitionScope.PokemonScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    uiState: PokemonUiState,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateUp
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.navigate_up)
                        )
                    }
                },
                windowInsets = WindowInsets.safeDrawing.only(
                    WindowInsetsSides.Horizontal + WindowInsetsSides.Top
                ),
                scrollBehavior = scrollBehavior
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing
    ) { innerPadding ->
        when (uiState) {
            PokemonUiState.Loading -> {
                Box(
                    modifier = modifier.padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is PokemonUiState.Success -> {
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .verticalScroll(scrollState)
                        .sharedBounds(
                            sharedContentState = rememberSharedContentState(uiState.id),
                            animatedVisibilityScope = animatedVisibilityScope,
                            resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                        )
                ) {
                    AsyncImage(
                        model = uiState.sprite,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(194.dp)
                            .background(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = MaterialTheme.shapes.medium
                            )
                    )
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.number, uiState.id),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = uiState.name,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
            is PokemonUiState.Error -> {
                Box(
                    modifier = modifier.padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = uiState.message.orEmpty()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PokemonScreenPreview() {
    AppTheme {
        SharedTransitionLayout {
            AnimatedVisibility(
                visible = true
            ) {
                PokemonScreen(
                    animatedVisibilityScope = this@AnimatedVisibility,
                    uiState = PokemonUiState.Success(
                        id = 1,
                        name = "Bulbasaur",
                        sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
                    ),
                    onNavigateUp = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
