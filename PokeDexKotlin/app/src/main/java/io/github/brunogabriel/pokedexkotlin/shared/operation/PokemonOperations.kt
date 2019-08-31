package io.github.brunogabriel.pokedexkotlin.shared.operation

import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon

/**
 * Created by brunogabriel on 2019-08-31.
 */
class PokemonOperations {
    companion object {
        val regex = """.*[//](\d+)[//]""".toRegex()
    }

    fun findPokemonNumber(pokemon: Pokemon) =
        regex.find(pokemon.url ?: "")?.destructured?.component1()?.toLong()
}