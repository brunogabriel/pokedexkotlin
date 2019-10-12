package io.github.brunogabriel.pokedexkotlin.shared.operations

import io.github.brunogabriel.pokedexkotlin.database.model.Pokemon

/**
 * Created by brunogabriel on 2019-10-11.
 */
class PokemonOperations {
    companion object {
        private val regex = """.*[/](\d+)[/]""".toRegex()
    }

    fun findPokemonNumber(pokemon: Pokemon) =
        regex.find(pokemon.url ?: "")?.destructured?.component1()?.toLong()
}