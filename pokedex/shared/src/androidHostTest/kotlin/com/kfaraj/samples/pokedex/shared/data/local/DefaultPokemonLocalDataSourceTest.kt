package com.kfaraj.samples.pokedex.shared.data.local

import androidx.paging.testing.asPagingSourceFactory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class DefaultPokemonLocalDataSourceTest {

    @Test
    fun upsertAll() = runTest {
        val pokemonEntities = listOf(BULBASAUR_ENTITY)
        val pokemonDao = mockk<PokemonDao> {
            coEvery { upsertAll(any()) } returns Unit
        }
        val pokemonLocalDataSource = DefaultPokemonLocalDataSource(
            pokemonDao
        )
        pokemonLocalDataSource.upsertAll(pokemonEntities)
        coVerify { pokemonDao.upsertAll(pokemonEntities) }
    }

    @Test
    fun get() = runTest {
        val pokemonDao = mockk<PokemonDao> {
            coEvery { get(1) } returns BULBASAUR_ENTITY
        }
        val pokemonLocalDataSource = DefaultPokemonLocalDataSource(
            pokemonDao
        )
        val result = pokemonLocalDataSource.get(1)
        assertEquals(BULBASAUR_ENTITY, result)
    }

    @Test
    fun getPagingSource() {
        val pagingSourceFactory = listOf(BULBASAUR_ENTITY).asPagingSourceFactory()
        val pagingSource = pagingSourceFactory()
        val pokemonDao = mockk<PokemonDao> {
            every { getPagingSource() } returns pagingSource
        }
        val pokemonLocalDataSource = DefaultPokemonLocalDataSource(
            pokemonDao
        )
        val result = pokemonLocalDataSource.getPagingSource()
        assertEquals(pagingSource, result)
    }

    @Test
    fun getCount() = runTest {
        val pokemonDao = mockk<PokemonDao> {
            coEvery { getCount() } returns 1
        }
        val pokemonLocalDataSource = DefaultPokemonLocalDataSource(
            pokemonDao
        )
        val result = pokemonLocalDataSource.getCount()
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
