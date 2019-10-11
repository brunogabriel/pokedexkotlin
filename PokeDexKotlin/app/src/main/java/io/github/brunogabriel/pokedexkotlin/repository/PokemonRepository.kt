package io.github.brunogabriel.pokedexkotlin.repository

import io.github.brunogabriel.pokedexkotlin.database.dao.PokemonDao
import io.github.brunogabriel.pokedexkotlin.database.model.Pokemon
import io.github.brunogabriel.pokedexkotlin.shared.networking.PokemonService
import io.reactivex.Flowable

/**
 * Created by brunogabriel on 2019-10-10.
 */
interface PokemonRepository {
    fun findPokemons(): Flowable<List<Pokemon>>
}

class PokemonRepositoryImpl(
    private val pokemonDao: PokemonDao,
    private val pokemonService: PokemonService
) : PokemonRepository {
    override fun findPokemons(): Flowable<List<Pokemon>> {
        return pokemonDao.findAll().flatMap { pokemonList ->
            if (pokemonList.isNotEmpty()) {
                Flowable.just(pokemonList)
            } else {
                fetchFromService()
            }
        }
    }

    private fun fetchFromService(): Flowable<List<Pokemon>> {
        return pokemonService.fetchPokemons().flatMapPublisher {
            pokemonDao.insertAll(it.results).andThen(pokemonDao.findAll())
        }
    }
}