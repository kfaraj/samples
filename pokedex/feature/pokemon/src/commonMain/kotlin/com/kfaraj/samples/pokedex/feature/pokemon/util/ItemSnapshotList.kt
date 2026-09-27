package com.kfaraj.samples.pokedex.feature.pokemon.util

import androidx.paging.ItemSnapshotList
import kotlin.jvm.JvmSuppressWildcards

/**
 * Returns a factory of stable and unique keys representing the item.
 */
internal fun <T : Any> ItemSnapshotList<T>.itemKey(
    key: ((item: @JvmSuppressWildcards T) -> Any)? = null
): (index: Int) -> Any {
    return { index ->
        if (key == null) {
            getPagingPlaceholderKey(index)
        } else {
            val item = this[index]
            if (item == null) getPagingPlaceholderKey(index) else key(item)
        }
    }
}

private fun getPagingPlaceholderKey(index: Int): Any = "PagingPlaceholderKey($index)"
