package io.github.brunogabriel.pokedexkotlin.main.pokelist.domain

import io.github.brunogabriel.pokedexkotlin.main.pokelist.data.models.Pokemon
import io.github.brunogabriel.pokedexkotlin.main.pokelist.data.models.response.PokemonResponse

fun pokemonResponseToPokemon(
    response: PokemonResponse
): Pokemon {
    return Pokemon(
        name = response.name,
        number = response.url.split("/".toRegex()).dropLast(1).last()
    )
}
