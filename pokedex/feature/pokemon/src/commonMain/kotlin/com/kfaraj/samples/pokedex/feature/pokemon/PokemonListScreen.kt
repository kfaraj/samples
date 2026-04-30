package com.kfaraj.samples.pokedex.feature.pokemon

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.kfaraj.samples.pokedex.core.ui.app_name
import com.kfaraj.samples.pokedex.core.ui.theme.AppTheme
import com.kfaraj.samples.pokedex.core.ui.util.plus
import kotlinx.coroutines.flow.flowOf
import org.jetbrains.compose.resources.stringResource

/**
 * Displays the Pokémon list UI state on the screen.
 */
@Composable
internal fun SharedTransitionScope.PokemonListScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: PokemonListViewModel,
    onItemClick: (item: PokemonListItemUiState?) -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyPagingItems = viewModel.pagingData.collectAsLazyPagingItems()
    PokemonListScreen(
        animatedVisibilityScope = animatedVisibilityScope,
        lazyPagingItems = lazyPagingItems,
        onItemClick = onItemClick,
        modifier = modifier
    )
}

/**
 * Displays the Pokémon list UI state on the screen.
 */
@Composable
private fun SharedTransitionScope.PokemonListScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    lazyPagingItems: LazyPagingItems<PokemonListItemUiState>,
    onItemClick: (item: PokemonListItemUiState?) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(com.kfaraj.samples.pokedex.core.ui.Res.string.app_name)
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
                count = lazyPagingItems.itemCount,
                key = lazyPagingItems.itemKey { it.id }
            ) { index ->
                val item = lazyPagingItems[index]
                PokemonListItem(
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
private fun PokemonListScreenPreview() {
    AppTheme {
        SharedTransitionLayout {
            AnimatedVisibility(
                visible = true
            ) {
                PokemonListScreen(
                    animatedVisibilityScope = this@AnimatedVisibility,
                    lazyPagingItems = flowOf(
                        PagingData.from(
                            listOf(
                                PokemonListItemUiState(
                                    id = 1,
                                    name = "Bulbasaur",
                                    sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
                                )
                            )
                        )
                    ).collectAsLazyPagingItems(),
                    onItemClick = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
