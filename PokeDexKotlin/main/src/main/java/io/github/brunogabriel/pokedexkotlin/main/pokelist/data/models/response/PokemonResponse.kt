package io.github.brunogabriel.pokedexkotlin.main.pokelist.data.models.response

import kotlinx.serialization.Serializable

@Serializable
class PokemonResponse(
    val name: String,
    val url: String
)