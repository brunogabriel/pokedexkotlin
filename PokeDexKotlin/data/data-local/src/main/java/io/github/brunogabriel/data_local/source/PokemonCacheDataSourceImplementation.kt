package io.github.brunogabriel.data_local.source

import io.github.brunogabriel.data_core.source.PokemonCacheDataSource
import io.github.brunogabriel.data_local.database.dao.PokemonDao
import io.github.brunogabriel.data_local.mapper.PokemonCacheMapper
import io.github.brunogabriel.domain.entities.Pokemon
import io.reactivex.Single

/**
 * Created by bruno on 28/02/20
 */
class PokemonCacheDataSourceImplementation(
    private val pokemonDao: PokemonDao
) : PokemonCacheDataSource {
    override fun fetchPhotos(): Single<List<Pokemon>> {
        return pokemonDao.findPhotos().map {
            PokemonCacheMapper.mapToPokemon(it)
        }
    }

    override fun insertData(data: List<Pokemon>) {
        pokemonDao.insertAll(PokemonCacheMapper.mapToCache(data))
    }

    override fun updateData(data: List<Pokemon>) {
        pokemonDao.update(PokemonCacheMapper.mapToCache(data))
    }
}
