package io.github.brunogabriel.pokedexkotlin.repository

import io.github.brunogabriel.pokedexkotlin.database.dao.PokemonDao
import io.github.brunogabriel.pokedexkotlin.database.model.Pokemon
import io.github.brunogabriel.pokedexkotlin.shared.networking.PokemonService
import io.github.brunogabriel.pokedexkotlin.shared.operations.PokemonOperations
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by brunogabriel on 2019-10-10.
 */
interface PokemonRepository {
    fun findPokemons(): Flowable<List<Pokemon>>
    fun updatePokemon(pokemon: Pokemon): Completable
}

class PokemonRepositoryImpl(
    private val pokemonDao: PokemonDao,
    private val pokemonService: PokemonService,
    private val pokemonOperations: PokemonOperations
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

    override fun updatePokemon(pokemon: Pokemon): Completable {
        return pokemonDao.updatePokemon(pokemon)
    }

    private fun fetchFromService(): Flowable<List<Pokemon>> {
        return pokemonService.fetchPokemons().flatMapPublisher { response ->
            response.results.forEach {
                it.number = pokemonOperations.findPokemonNumber(it)
            }
            pokemonDao.insertAll(response.results).andThen(pokemonDao.findAll())
        }
    }
}