package io.github.brunogabriel.data_remote.mapper

import io.github.brunogabriel.data_remote.models.PokemonPayload
import io.github.brunogabriel.domain.entities.Pokemon

/**
 * Created by bruno on 27/02/20
 */
private const val REGEX_NUMBER = """.*[/](\d+)[/]"""
private const val SPRITE_BASE_URL =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
private val numberRegex = REGEX_NUMBER.toRegex()

fun PokemonPayload.toPokemon(): Pokemon {
    val number = numberRegex.find(this.url)?.destructured?.component1()?.toInt() ?: 0
    return Pokemon(
        number,
        this.name,
        "$SPRITE_BASE_URL$number.png"
    )
}
