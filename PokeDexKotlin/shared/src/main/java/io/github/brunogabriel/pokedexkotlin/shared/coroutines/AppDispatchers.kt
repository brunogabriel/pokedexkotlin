package io.github.brunogabriel.pokedexkotlin.shared.coroutines

import kotlinx.coroutines.CoroutineDispatcher

data class AppDispatchers(
    val default: CoroutineDispatcher,
    val io: CoroutineDispatcher,
    val main: CoroutineDispatcher
)