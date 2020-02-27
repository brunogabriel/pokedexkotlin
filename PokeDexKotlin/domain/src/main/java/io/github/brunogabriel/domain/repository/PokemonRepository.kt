package io.github.brunogabriel.domain.repository

import io.github.brunogabriel.domain.entities.Pokemon
import io.reactivex.Single

/**
 * Created by bruno on 27/02/20
 */
interface PokemonRepository {
    fun fetchPokemons(forceUpdate: Boolean): Single<List<Pokemon>>
}