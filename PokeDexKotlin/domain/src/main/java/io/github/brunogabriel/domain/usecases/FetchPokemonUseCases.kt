package io.github.brunogabriel.domain.usecases

import io.github.brunogabriel.domain.entities.Pokemon
import io.github.brunogabriel.domain.repository.PokemonRepository
import io.reactivex.Scheduler
import io.reactivex.Single

/**
 * Created by bruno on 27/02/20
 */
class FetchPokemonUseCases(
    private val repository: PokemonRepository,
    private val scheduler: Scheduler
) {
    fun fetchPokemons(forceUpdate: Boolean): Single<List<Pokemon>> {
        return repository.fetchPokemons(forceUpdate)
            .subscribeOn(scheduler)
    }
}