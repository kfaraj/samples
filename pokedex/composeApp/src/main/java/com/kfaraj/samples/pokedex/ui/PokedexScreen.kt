package com.kfaraj.samples.pokedex.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kfaraj.samples.pokedex.R
import com.kfaraj.samples.pokedex.shared.ui.PokedexItemUiState
import com.kfaraj.samples.pokedex.shared.ui.PokedexViewModel
import com.kfaraj.samples.pokedex.ui.theme.AppTheme
import com.kfaraj.samples.pokedex.util.plus

/**
 * Displays the Pokédex UI state on the screen.
 */
@Composable
fun SharedTransitionScope.PokedexScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: PokedexViewModel,
    onItemClick: (item: PokedexItemUiState?) -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyPagingItems = viewModel.pagingData.collectAsStateWithLifecycle()
    PokedexScreen(
        animatedVisibilityScope = animatedVisibilityScope,
        lazyPagingItems = lazyPagingItems.value,
        onItemClick = onItemClick,
        modifier = modifier
    )
}

/**
 * Displays the Pokédex UI state on the screen.
 */
@Composable
private fun SharedTransitionScope.PokedexScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    lazyPagingItems: List<PokedexItemUiState>,
    onItemClick: (item: PokedexItemUiState?) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name)
                    )
                },
                windowInsets = WindowInsets.safeDrawing.only(
                    WindowInsetsSides.Horizontal + WindowInsetsSides.Top
                ),
                scrollBehavior = scrollBehavior
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing
    ) { innerPadding ->
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = innerPadding + PaddingValues(8.dp),
            verticalItemSpacing = 8.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                count = lazyPagingItems.size,
                key = { lazyPagingItems[it].id }
            ) { index ->
                val item = lazyPagingItems[index]
                PokedexItem(
                    item = item,
                    onItemClick = {
                        onItemClick(item)
                    },
                    modifier = Modifier.sharedBounds(
                        sharedContentState = rememberSharedContentState(item?.id ?: 0),
                        animatedVisibilityScope = animatedVisibilityScope,
                        resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun PokedexScreenPreview() {
    AppTheme {
        SharedTransitionLayout {
            AnimatedVisibility(
                visible = true
            ) {
                PokedexScreen(
                    animatedVisibilityScope = this@AnimatedVisibility,
                    lazyPagingItems = listOf(
                        PokedexItemUiState(
                            id = 1,
                            name = "Bulbasaur",
                            sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
                        )
                    ),
                    onItemClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
