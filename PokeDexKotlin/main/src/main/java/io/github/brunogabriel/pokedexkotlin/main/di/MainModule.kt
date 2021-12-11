package io.github.brunogabriel.pokedexkotlin.main.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.brunogabriel.pokedexkotlin.main.presentation.MainActivity

@Module(
    includes = [
        MainActivityModule::class
    ]
)
abstract class MainModule

@Module
internal abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract fun bindActivity(): MainActivity
}