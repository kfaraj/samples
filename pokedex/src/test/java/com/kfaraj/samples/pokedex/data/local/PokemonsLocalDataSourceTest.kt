package com.kfaraj.samples.pokedex.data.local

import com.kfaraj.samples.pokedex.testutils.TestPagingSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class PokemonsLocalDataSourceTest {

    @Test
    fun insertAll() = runTest {
        val pokemons = listOf(BULBASAUR_ENTITY)
        val pokemonDao = mock<PokemonDao>()
        val pokemonsLocalDataSource = PokemonsLocalDataSource(
            pokemonDao
        )
        pokemonsLocalDataSource.insertAll(pokemons)
        verify(pokemonDao).insertAll(pokemons)
    }

    @Test
    fun get() = runTest {
        val pokemonDao = mock<PokemonDao>().apply {
            whenever(get(1)).thenReturn(BULBASAUR_ENTITY)
        }
        val pokemonsLocalDataSource = PokemonsLocalDataSource(
            pokemonDao
        )
        val result = pokemonsLocalDataSource.get(1)
        assertEquals(BULBASAUR_ENTITY, result)
    }

    @Test
    fun getPagingSource() {
        val pagingSource = TestPagingSource<Int, PokemonEntity>(listOf(BULBASAUR_ENTITY))
        val pokemonDao = mock<PokemonDao>().apply {
            whenever(getPagingSource()).thenReturn(pagingSource)
        }
        val pokemonsLocalDataSource = PokemonsLocalDataSource(
            pokemonDao
        )
        val result = pokemonsLocalDataSource.getPagingSource()
        assertEquals(pagingSource, result)
    }

    @Test
    fun getCount() = runTest {
        val pokemonDao = mock<PokemonDao>().apply {
            whenever(getCount()).thenReturn(1)
        }
        val pokemonsLocalDataSource = PokemonsLocalDataSource(
            pokemonDao
        )
        val result = pokemonsLocalDataSource.getCount()
        assertEquals(1, result)
    }

    companion object {
        private val BULBASAUR_ENTITY = PokemonEntity(1, "bulbasaur")
    }

}
