package io.github.brunogabriel.data_core.source

import io.github.brunogabriel.domain.entities.Pokemon
import io.reactivex.Single

/**
 * Created by bruno on 27/02/20
 */
interface PokemonCacheDataSource {
    fun fetchPokemons(): Single<List<Pokemon>>
    fun insertData(data: List<Pokemon>)
    fun updateData(data: List<Pokemon>)
}