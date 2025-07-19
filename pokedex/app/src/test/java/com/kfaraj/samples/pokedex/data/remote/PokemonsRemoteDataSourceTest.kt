package com.kfaraj.samples.pokedex.data.remote

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class PokemonsRemoteDataSourceTest {

    @Test
    fun getPokemonSpecies() = runTest {
        val response = NamedApiResourceList(1, null, null, listOf(BULBASAUR_SPECIES_API_RESOURCE))
        val pokeApiService = mock<PokeApiService>().apply {
            whenever(getPokemonSpecies(1, 0)).thenReturn(response)
        }
        val pokemonsRemoteDataSource = PokemonsRemoteDataSource(
            pokeApiService
        )
        val result = pokemonsRemoteDataSource.getPokemonSpecies(1, 0)
        assertEquals(response, result)
    }

    companion object {
        private val BULBASAUR_SPECIES_API_RESOURCE = NamedApiResource(
            "bulbasaur",
            "https://pokeapi.co/api/v2/pokemon-species/1/"
        )
    }

}
