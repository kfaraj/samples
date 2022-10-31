package com.kfaraj.samples.pokedex.testutils

import androidx.recyclerview.widget.DiffUtil

/**
 * Provides a test double for [DiffUtil.ItemCallback].
 */
class TestItemCallback<T : Any> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return true
    }

}
