package io.github.brunogabriel.styleguide.components.models

// TODO: Refactoring to rename
data class PokemonCardViewModel(
    val name: String,
    val number: String,
    val imageUrl: String,
    val type1: String,
    val type2: String?
)