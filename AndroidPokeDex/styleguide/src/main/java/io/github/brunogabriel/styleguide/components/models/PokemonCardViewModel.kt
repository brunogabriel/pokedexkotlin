package io.github.brunogabriel.styleguide.components.models

data class PokemonCardViewModel(
    val name: String,
    val number: String,
    val imageUrl: String,
    val type1: String,
    val type2: String?
)