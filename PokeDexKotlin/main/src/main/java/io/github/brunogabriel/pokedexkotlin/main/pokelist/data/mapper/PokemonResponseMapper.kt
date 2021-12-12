package io.github.brunogabriel.pokedexkotlin.main.pokelist.data.mapper

import io.github.brunogabriel.pokedexkotlin.main.pokelist.data.models.entity.PokemonEntity
import io.github.brunogabriel.pokedexkotlin.main.pokelist.data.models.response.PokemonResponse

fun responseToEntity(response: PokemonResponse) = PokemonEntity(
    number = response.url.split("/".toRegex()).dropLast(1).last(),
    name = response.name,
    url = response.url
)