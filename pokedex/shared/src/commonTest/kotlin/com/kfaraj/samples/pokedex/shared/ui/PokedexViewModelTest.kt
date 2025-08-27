package com.kfaraj.samples.pokedex.shared.ui

import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import com.kfaraj.samples.pokedex.shared.data.Pokemon
import com.kfaraj.samples.pokedex.shared.data.PokemonsRepository
import com.kfaraj.samples.pokedex.shared.testutils.MainDispatcherRule
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class PokedexViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun pagingData() = runTest {
        val pagingData = flowOf(
            PagingData.from(
                listOf(BULBASAUR),
                LoadStates(
                    LoadState.NotLoading(true),
                    LoadState.NotLoading(true),
                    LoadState.NotLoading(true)
                )
            )
        )
        val pokemonsRepository = mock<PokemonsRepository>().apply {
            whenever(getPagingDataStream(any())).thenReturn(pagingData)
        }
        val viewModel = PokedexViewModel(
            pokemonsRepository
        )
        val result = viewModel.pagingData.asSnapshot()
        assertEquals(listOf(BULBASAUR_UI_STATE), result)
    }

    companion object {
        private val BULBASAUR = Pokemon(
            1,
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        )
        private val BULBASAUR_UI_STATE = PokedexItemUiState(
            1,
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        )
    }

}
