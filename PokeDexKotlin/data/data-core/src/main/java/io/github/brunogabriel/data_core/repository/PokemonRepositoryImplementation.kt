package io.github.brunogabriel.data_core.repository

import io.github.brunogabriel.data_core.source.PokemonCacheDataSource
import io.github.brunogabriel.data_core.source.PokemonRemoteDataSource
import io.github.brunogabriel.domain.entities.Pokemon
import io.github.brunogabriel.domain.repository.PokemonRepository
import io.reactivex.Single

/**
 * Created by bruno on 27/02/20
 */
class PokemonRepositoryImplementation(
    private val cacheDataSource: PokemonCacheDataSource,
    private val remoteDataSource: PokemonRemoteDataSource
) : PokemonRepository {
    override fun fetchPokemons(forceUpdate: Boolean): Single<List<Pokemon>> {
        return if (forceUpdate) {
            findFromRemoteDataSource(forceUpdate)
        } else {
            cacheDataSource.fetchPhotos().flatMap { result ->
                when {
                    result.isEmpty() -> findFromRemoteDataSource(false)
                    else -> Single.just(result)
                }
            }
        }
    }

    private fun findFromRemoteDataSource(forceUpdate: Boolean): Single<List<Pokemon>> {
        return remoteDataSource.findPokemons()
            .flatMap {
                if (forceUpdate) {
                    cacheDataSource.updateData(it)
                } else {
                    cacheDataSource.insertData(it)
                }
                Single.just(it)
            }
    }
}