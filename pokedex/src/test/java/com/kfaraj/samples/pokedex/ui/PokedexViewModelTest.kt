package com.kfaraj.samples.pokedex.ui

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import com.kfaraj.samples.pokedex.data.Pokemon
import com.kfaraj.samples.pokedex.data.PokemonsRepository
import com.kfaraj.samples.pokedex.domain.GetSpriteUseCase
import com.kfaraj.samples.pokedex.testutils.MainDispatcherRule
import com.kfaraj.samples.pokedex.testutils.TestItemCallback
import com.kfaraj.samples.pokedex.testutils.TestListUpdateCallback
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class PokedexViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun pagingData() = runTest {
        val pagingData = flowOf(PagingData.from(listOf(BULBASAUR)))
        val pokemonsRepository = mock<PokemonsRepository>().apply {
            whenever(getPagingDataStream(any())).thenReturn(pagingData)
        }
        val getSpriteUseCase = mock<GetSpriteUseCase>().apply {
            whenever(invoke(1)).thenReturn("/1.png")
        }
        val viewModel = PokedexViewModel(
            pokemonsRepository,
            getSpriteUseCase
        )
        val result = viewModel.pagingData.first()
        val differ = AsyncPagingDataDiffer(
            TestItemCallback<PokedexItemUiState>(),
            TestListUpdateCallback(),
            mainDispatcherRule.testDispatcher,
            mainDispatcherRule.testDispatcher
        )
        differ.submitData(result)
        assertEquals(listOf(BULBASAUR_UI_STATE), differ.snapshot().items)
    }

    companion object {
        private val BULBASAUR = Pokemon(1, "bulbasaur")
        private val BULBASAUR_UI_STATE = PokedexItemUiState(1, "bulbasaur", "/1.png")
    }

}
