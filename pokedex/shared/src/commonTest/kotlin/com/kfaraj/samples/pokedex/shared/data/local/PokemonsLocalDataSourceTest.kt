package com.kfaraj.samples.pokedex.shared.data.local

import androidx.paging.testing.asPagingSourceFactory
import kotlinx.coroutines.test.runTest
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class PokemonsLocalDataSourceTest {

    @Test
    fun upsertAll() = runTest {
        val pokemons = listOf(BULBASAUR_ENTITY)
        val pokemonDao = mock<PokemonDao>()
        val pokemonsLocalDataSource = PokemonsLocalDataSource(
            pokemonDao
        )
        pokemonsLocalDataSource.upsertAll(pokemons)
        verify(pokemonDao).upsertAll(pokemons)
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
        val pagingSourceFactory = listOf(BULBASAUR_ENTITY).asPagingSourceFactory()
        val pagingSource = pagingSourceFactory()
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
        private val BULBASAUR_ENTITY = PokemonEntity(
            1,
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        )
    }

}
