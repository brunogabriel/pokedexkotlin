package io.github.brunogabriel.data_local.source

import io.github.brunogabriel.data_core.source.PokemonCacheDataSource
import io.github.brunogabriel.data_local.database.dao.PokemonDao
import io.github.brunogabriel.data_local.mapper.toPokemon
import io.github.brunogabriel.data_local.mapper.toPokemonCache
import io.github.brunogabriel.domain.entities.Pokemon
import io.reactivex.Single

/**
 * Created by bruno on 28/02/20
 */
class PokemonCacheDataSourceImplementation(
    private val pokemonDao: PokemonDao
) : PokemonCacheDataSource {
    override fun fetchPokemons(): Single<List<Pokemon>> {
        return pokemonDao.findPokemons().map { cache ->
            cache.map { it.toPokemon() }
        }
    }

    override fun insertData(data: List<Pokemon>) {
        pokemonDao.insertAll(data.map { it.toPokemonCache() })
    }

    override fun updateData(data: List<Pokemon>) {
        pokemonDao.update(data.map { it.toPokemonCache() })
    }
}
