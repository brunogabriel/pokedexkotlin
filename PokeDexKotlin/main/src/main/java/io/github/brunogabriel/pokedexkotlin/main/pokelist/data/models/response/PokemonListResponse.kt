package io.github.brunogabriel.pokedexkotlin.main.pokelist.data.models.response

import kotlinx.serialization.Serializable

@Serializable
class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val result: List<PokemonResponse>
)