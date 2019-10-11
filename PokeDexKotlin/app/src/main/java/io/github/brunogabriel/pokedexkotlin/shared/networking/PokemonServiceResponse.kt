package io.github.brunogabriel.pokedexkotlin.shared.networking

import io.github.brunogabriel.pokedexkotlin.database.model.Pokemon

/**
 * Created by brunogabriel on 2019-08-31.
 */
data class PokemonServiceResponse(
    val count: Int,
    val next: String? = null,
    val results: List<Pokemon>
)