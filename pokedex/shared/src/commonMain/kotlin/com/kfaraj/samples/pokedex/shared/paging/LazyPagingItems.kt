package com.kfaraj.samples.pokedex.shared.paging

import androidx.paging.ItemSnapshotList
import androidx.paging.PagingData
import androidx.paging.PagingDataEvent
import androidx.paging.PagingDataPresenter
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModelScope
import com.rickclephas.kmp.observableviewmodel.launch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

class LazyPagingItems<T : Any>(
    private val viewModelScope: ViewModelScope,
    private val flow: Flow<PagingData<T>>
) {

    /**
     * If the [flow] is a SharedFlow, it is expected to be the flow returned by from
     * pager.flow.cachedIn(scope) which could contain a cached PagingData. We pass the cached
     * PagingData to the presenter so that if the PagingData contains cached data, the presenter can
     * be initialized with the data prior to collection on pager.
     */
    private val pagingDataPresenter =
        object :
            PagingDataPresenter<T>(
                cachedPagingData =
                    if (flow is SharedFlow<PagingData<T>>) flow.replayCache.firstOrNull() else null,
            ) {
            override suspend fun presentPagingDataEvent(event: PagingDataEvent<T>) {
                updateItemSnapshotList()
            }
        }

    /**
     * Contains the immutable [ItemSnapshotList] of currently presented items, including any
     * placeholders if they are enabled. Note that similarly to [peek] accessing the items in a list
     * will not trigger any loads. Use [get] to achieve such behavior.
     */
    val itemSnapshotList: MutableStateFlow<ItemSnapshotList<T>> =
        MutableStateFlow(viewModelScope, pagingDataPresenter.snapshot())

    /** The number of items which can be accessed. */
    val itemCount: Int
        get() = itemSnapshotList.value.size

    init {
        viewModelScope.launch {
            flow.collectLatest {
                pagingDataPresenter.collectFrom(it)
            }
        }
    }

    private fun updateItemSnapshotList() {
        itemSnapshotList.value = pagingDataPresenter.snapshot()
    }

    /**
     * Returns the presented item at the specified position, notifying Paging of the item access to
     * trigger any loads necessary to fulfill prefetchDistance.
     *
     * @see peek
     */
    operator fun get(index: Int): T? {
        pagingDataPresenter[index] // this registers the value load
        return itemSnapshotList.value[index]
    }

    /**
     * Returns the presented item at the specified position, without notifying Paging of the item
     * access that would normally trigger page loads.
     *
     * @param index Index of the presented item to return, including placeholders.
     * @return The presented item at position [index], `null` if it is a placeholder
     */
    fun peek(index: Int): T? {
        return itemSnapshotList.value[index]
    }

}
