package com.kfaraj.samples.pokedex.data.pokemon.util

import androidx.paging.PagingSource
import androidx.paging.PagingState

/**
 * Returns a [PagingSource] containing the result of applying the given [transform] to each element,
 * as it is loaded.
 */
internal fun <T : Any, R : Any> PagingSource<Int, T>.map(
    transform: suspend (T) -> R
): PagingSource<Int, R> = object : PagingSource<Int, R>() {
    init {
        this@map.registerInvalidatedCallback {
            invalidate()
        }
    }

    override val jumpingSupported: Boolean
        get() = this@map.jumpingSupported

    override val keyReuseSupported: Boolean
        get() = this@map.keyReuseSupported

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, R> {
        return when (val result = this@map.load(params)) {
            is LoadResult.Error -> LoadResult.Error(
                throwable = result.throwable
            )
            is LoadResult.Invalid -> LoadResult.Invalid(
            )
            is LoadResult.Page -> LoadResult.Page(
                data = result.data.map { transform(it) },
                prevKey = result.prevKey,
                nextKey = result.nextKey,
                itemsBefore = result.itemsBefore,
                itemsAfter = result.itemsAfter
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, R>): Int? {
        return this@map.getRefreshKey(
            PagingState(
                pages = emptyList(),
                anchorPosition = state.anchorPosition,
                config = state.config,
                leadingPlaceholderCount = 0
            )
        )
    }
}
