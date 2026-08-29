package com.kfaraj.samples.pokedex.data.pokemon

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.RemoteMediator
import com.kfaraj.samples.pokedex.data.pokemon.local.PokemonEntity
import com.kfaraj.samples.pokedex.data.pokemon.local.PokemonLocalDataSource
import com.kfaraj.samples.pokedex.data.pokemon.remote.PokemonRemoteDataSource
import com.kfaraj.samples.pokedex.data.pokemon.util.map
import org.koin.core.annotation.Single

/**
 * Provides a default implementation of [PokemonRepository].
 */
@Single
internal class DefaultPokemonRepository(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val pokemonLocalDataSource: PokemonLocalDataSource
) : PokemonRepository {

    override suspend fun get(id: Int): Pokemon {
        return pokemonLocalDataSource.get(id).toPokemon()
    }

    override fun getPagingSource(): PagingSource<Int, Pokemon> {
        return pokemonLocalDataSource.getPagingSource().map { it.toPokemon() }
    }

    @ExperimentalPagingApi
    override fun getRemoteMediator(): RemoteMediator<Int, Pokemon> {
        return PokemonRemoteMediator(pokemonRemoteDataSource, pokemonLocalDataSource)
    }

    /**
     * Converts the model from the local data source to the data layer.
     */
    private fun PokemonEntity.toPokemon(): Pokemon {
        return Pokemon(
            id,
            name,
            sprite
        )
    }

}
