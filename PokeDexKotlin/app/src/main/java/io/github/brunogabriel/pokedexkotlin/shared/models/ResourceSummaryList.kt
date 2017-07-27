package io.github.brunogabriel.pokedexkotlin.shared.models

/**
 * Created by bruno on 7/26/17.
 */
data class ResourceSummaryList(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>?
)