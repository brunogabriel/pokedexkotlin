package io.github.brunogabriel.pokedexkotlin.network.di

import dagger.Module

@Module(
    includes = [
        RetrofitModule::class
    ]
)
abstract class NetworkModule