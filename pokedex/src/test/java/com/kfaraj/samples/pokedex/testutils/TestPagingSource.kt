package com.kfaraj.samples.pokedex.testutils

import androidx.paging.PagingSource
import androidx.paging.PagingState

/**
 * Provides a test double for [PagingSource].
 */
class TestPagingSource<Key : Any, Value : Any>(
    private val data: List<Value> = emptyList()
) : PagingSource<Key, Value>() {

    override suspend fun load(params: LoadParams<Key>): LoadResult<Key, Value> {
        return LoadResult.Page(data, null, null)
    }

    override fun getRefreshKey(state: PagingState<Key, Value>): Key? {
        return null
    }

}
