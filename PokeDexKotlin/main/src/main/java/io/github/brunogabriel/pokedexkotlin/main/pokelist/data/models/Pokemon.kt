package io.github.brunogabriel.pokedexkotlin.main.pokelist.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val name: String,
    val number: String
) {
    fun getSprite() =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$number.png"
}