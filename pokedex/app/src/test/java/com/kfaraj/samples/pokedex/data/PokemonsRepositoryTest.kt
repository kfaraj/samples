package com.kfaraj.samples.pokedex.data

import androidx.paging.PagingConfig
import androidx.paging.testing.asPagingSourceFactory
import androidx.paging.testing.asSnapshot
import com.kfaraj.samples.pokedex.data.local.PokemonEntity
import com.kfaraj.samples.pokedex.data.local.PokemonsLocalDataSource
import com.kfaraj.samples.pokedex.data.remote.NamedApiResourceList
import com.kfaraj.samples.pokedex.data.remote.PokemonsRemoteDataSource
import com.kfaraj.samples.pokedex.testutils.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class PokemonsRepositoryTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun get() = runTest {
        val pokemonsRemoteDataSource = mock<PokemonsRemoteDataSource>()
        val pokemonsLocalDataSource = mock<PokemonsLocalDataSource>().apply {
            whenever(get(1)).thenReturn(BULBASAUR_ENTITY)
        }
        val pokemonsRepository = PokemonsRepository(
            pokemonsRemoteDataSource,
            pokemonsLocalDataSource
        )
        val result = pokemonsRepository.get(1)
        assertEquals(BULBASAUR, result)
    }

    @Test
    fun getPagingDataStream() = runTest {
        val response = NamedApiResourceList(1, null, "/", emptyList())
        val pokemonsRemoteDataSource = mock<PokemonsRemoteDataSource>().apply {
            whenever(getPokemonSpecies(1, 1)).thenReturn(response)
        }
        val pagingSourceFactory = listOf(BULBASAUR_ENTITY).asPagingSourceFactory()
        val pagingSource = pagingSourceFactory()
        val pokemonsLocalDataSource = mock<PokemonsLocalDataSource>().apply {
            whenever(getPagingSource()).thenReturn(pagingSource)
            whenever(getCount()).thenReturn(1)
        }
        val pokemonsRepository = PokemonsRepository(
            pokemonsRemoteDataSource,
            pokemonsLocalDataSource
        )
        val result = pokemonsRepository.getPagingDataStream(PagingConfig(1)).asSnapshot()
        assertEquals(listOf(BULBASAUR), result)
    }

    companion object {
        private val BULBASAUR_ENTITY = PokemonEntity(
            1,
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        )
        private val BULBASAUR = Pokemon(
            1,
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        )
    }

}
