package io.github.brunogabriel.pokedexkotlin.main.di

import dagger.Module
import io.github.brunogabriel.pokedexkotlin.main.base.di.MainActivityModule

@Module(
    includes = [
        MainActivityModule::class
    ]
)
abstract class MainModule