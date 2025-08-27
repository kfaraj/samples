package com.kfaraj.samples.pokedex.shared.data.remote

import kotlinx.coroutines.test.runTest
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

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
