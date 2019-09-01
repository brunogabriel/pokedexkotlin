package io.github.brunogabriel.pokedexkotlin.shared.operation

import android.accounts.NetworkErrorException
import io.github.brunogabriel.pokedexkotlin.shared.database.PokemonRepository
import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon
import io.github.brunogabriel.pokedexkotlin.shared.model.PokemonServiceResponse
import io.github.brunogabriel.pokedexkotlin.shared.networking.PokemonService
import io.github.brunogabriel.pokedexkotlin.shared.networking.RetrofitManager
import io.reactivex.Observable
import retrofit2.Response

/**
 * Created by brunogabriel on 2019-08-31.
 */
class PokemonOperations(
    private val repository: PokemonRepository = PokemonRepository(),
    private val pokemonService: PokemonService = RetrofitManager.createService(PokemonService::class.java)) {

    companion object {
        val regex = """.*[//](\d+)[//]""".toRegex()
    }

    private fun findPokemonNumber(pokemon: Pokemon) = regex.find(pokemon.url ?: "")?.destructured?.component1()?.toLong()

    private fun isValidResponse(response: Response<PokemonServiceResponse>) = response.isSuccessful && response.body()?.results != null

    fun findPokemonList(): Observable<List<Pokemon>> {
        val pokemonList = repository.findAll(Pokemon::class.java)
        return if (pokemonList.isNotEmpty()) {
            Observable.just(pokemonList)
        } else {
            pokemonService.findPokemons()
                .doOnNext { response ->
                    if (isValidResponse(response)) {
                        response.body()!!.results.forEach { it.number = findPokemonNumber(it) }
                    }
                }
                .flatMap { response ->
                    return@flatMap if (isValidResponse(response)) {
                        repository.saveAll(response.body()!!.results)
                        Observable.just(response.body()!!.results)
                    } else {
                        Observable.error(NetworkErrorException("Fail getting pokemons from API"))
                    }
                }
        }
    }
}