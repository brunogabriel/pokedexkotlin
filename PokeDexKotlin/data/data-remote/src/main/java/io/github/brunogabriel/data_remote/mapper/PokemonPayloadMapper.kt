package io.github.brunogabriel.data_remote.mapper

import io.github.brunogabriel.data_remote.models.PokemonPayload
import io.github.brunogabriel.domain.entities.Pokemon

/**
 * Created by bruno on 27/02/20
 */
object PokemonPayloadMapper {
    private const val REGEX_NUMBER = """.*[/](\d+)[/]"""
    private const val SPRITE_BASE_URL =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"

    fun mapToPokemon(pokemonPayload: List<PokemonPayload>): List<Pokemon> {
        val numberRegex = REGEX_NUMBER.toRegex()
        return pokemonPayload.map {
            val number = numberRegex.find(it.url)?.destructured?.component1()?.toInt() ?: 0
            Pokemon(
                number,
                it.name,
                "$SPRITE_BASE_URL$number.png"
            )
        }
    }
}
