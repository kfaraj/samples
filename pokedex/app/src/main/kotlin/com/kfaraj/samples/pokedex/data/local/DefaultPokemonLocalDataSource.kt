package com.kfaraj.samples.pokedex.data.local

import androidx.paging.PagingSource
import org.koin.core.annotation.Factory

/**
 * Provides a default implementation of [PokemonLocalDataSource].
 */
@Factory
internal class DefaultPokemonLocalDataSource(
    private val pokemonDao: PokemonDao
) : PokemonLocalDataSource {

    override suspend fun upsertAll(pokemonEntities: List<PokemonEntity>) {
        pokemonDao.upsertAll(pokemonEntities)
    }

    override suspend fun get(id: Int): PokemonEntity {
        return pokemonDao.get(id)
    }

    override fun getPagingSource(): PagingSource<Int, PokemonEntity> {
        return pokemonDao.getPagingSource()
    }

    override suspend fun getCount(): Int {
        return pokemonDao.getCount()
    }

}
