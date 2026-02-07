package com.kfaraj.samples.pokedex.data.local

import androidx.paging.testing.asPagingSourceFactory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class PokemonsLocalDataSourceTest {

    @Test
    fun upsertAll() = runTest {
        val pokemons = listOf(BULBASAUR_ENTITY)
        val pokemonDao = mockk<PokemonDao> {
            coEvery { upsertAll(any()) } returns Unit
        }
        val pokemonsLocalDataSource = PokemonsLocalDataSource(
            pokemonDao
        )
        pokemonsLocalDataSource.upsertAll(pokemons)
        coVerify { pokemonDao.upsertAll(pokemons) }
    }

    @Test
    fun get() = runTest {
        val pokemonDao = mockk<PokemonDao> {
            coEvery { get(1) } returns BULBASAUR_ENTITY
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
        val pokemonDao = mockk<PokemonDao> {
            every { getPagingSource() } returns pagingSource
        }
        val pokemonsLocalDataSource = PokemonsLocalDataSource(
            pokemonDao
        )
        val result = pokemonsLocalDataSource.getPagingSource()
        assertEquals(pagingSource, result)
    }

    @Test
    fun getCount() = runTest {
        val pokemonDao = mockk<PokemonDao> {
            coEvery { getCount() } returns 1
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
