package io.github.brunogabriel.pokedexkotlin.main.pokelist.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class PokemonVO(
    val name: String,
    val number: String
) {
    fun getSprite() =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$number.png"

    fun getFormattedNumber() =
        "#${"0".repeat(3 - number.length)}$number"
}