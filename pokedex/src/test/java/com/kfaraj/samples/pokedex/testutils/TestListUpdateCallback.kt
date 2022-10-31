package com.kfaraj.samples.pokedex.testutils

import androidx.recyclerview.widget.ListUpdateCallback

/**
 * Provides a test double for [ListUpdateCallback].
 */
class TestListUpdateCallback : ListUpdateCallback {

    override fun onInserted(position: Int, count: Int) {
    }

    override fun onRemoved(position: Int, count: Int) {
    }

    override fun onMoved(fromPosition: Int, toPosition: Int) {
    }

    override fun onChanged(position: Int, count: Int, payload: Any?) {
    }

}
