package io.github.brunogabriel.pokedexkotlin.di

import io.github.brunogabriel.pokedexkotlin.shared.operations.PokemonOperations
import org.koin.dsl.module

/**
 * Created by brunogabriel on 2019-10-11.
 */
val operationsModule = module {
    factory { PokemonOperations() }
}