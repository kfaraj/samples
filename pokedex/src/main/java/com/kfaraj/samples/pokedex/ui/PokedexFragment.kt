package com.kfaraj.samples.pokedex.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.kfaraj.samples.pokedex.R
import com.kfaraj.samples.pokedex.domain.FormatIdUseCase
import com.kfaraj.samples.pokedex.domain.FormatNameUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Displays the Pokédex UI state on the screen.
 */
@AndroidEntryPoint
class PokedexFragment : Fragment(R.layout.fragment_pokedex) {

    @Inject
    lateinit var formatIdUseCase: FormatIdUseCase

    @Inject
    lateinit var formatNameUseCase: FormatNameUseCase

    private val viewModel by viewModels<PokedexViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = ViewCompat.requireViewById<RecyclerView>(view, android.R.id.list)
        val adapter = PokedexItemAdapter(PokedexItemCallback(), formatIdUseCase, formatNameUseCase)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pagingData.collectLatest { pagingData ->
                    adapter.submitData(pagingData)
                }
            }
        }
    }

}
