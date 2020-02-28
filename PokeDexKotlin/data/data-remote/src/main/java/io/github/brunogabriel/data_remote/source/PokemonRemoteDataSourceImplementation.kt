package io.github.brunogabriel.data_remote.source

import io.github.brunogabriel.data_core.source.PokemonRemoteDataSource
import io.github.brunogabriel.data_remote.mapper.PokemonPayloadMapper
import io.github.brunogabriel.data_remote.service.PokemonListService
import io.github.brunogabriel.domain.entities.Pokemon
import io.reactivex.Single

/**
 * Created by bruno on 27/02/20
 */
class PokemonRemoteDataSourceImplementation(
    private val service: PokemonListService
) : PokemonRemoteDataSource {
    override fun findPokemons(): Single<List<Pokemon>> {
        return service.findPokemons().map {
            PokemonPayloadMapper.mapToPokemon(
                it.results
            )
        }
    }
}
