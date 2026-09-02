package com.kfaraj.samples.pokedex.feature.pokemon.testutils

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator

/**
 * Provides a fake implementation of [RemoteMediator].
 */
@ExperimentalPagingApi
class FakeRemoteMediator<Key : Any, Value : Any>(
    private val result: MediatorResult
) : RemoteMediator<Key, Value>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Key, Value>
    ): MediatorResult {
        return result
    }

}
