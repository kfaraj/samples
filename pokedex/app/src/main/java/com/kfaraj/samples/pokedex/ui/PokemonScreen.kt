package com.kfaraj.samples.pokedex.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.kfaraj.samples.pokedex.R
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
                            painter = painterResource(R.drawable.ic_arrow_back),
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
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(uiState.id ?: 0),
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
                    text = uiState.id?.let { stringResource(R.string.number, it) } ?: "",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = uiState.name ?: "",
                    style = MaterialTheme.typography.bodyMedium
                )
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
                    uiState = PokemonUiState(
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
