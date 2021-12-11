package io.github.brunogabriel.pokedexkotlin.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.brunogabriel.pokedexkotlin.FakeSplashActivity
import io.github.brunogabriel.pokedexkotlin.main.di.MainModule
import io.github.brunogabriel.pokedexkotlin.shared.di.SharedModule

@Module(
    includes = [
        MainModule::class,
        SharedModule::class,
        FakeSplashModule::class // TODO necessary?
    ]
)
abstract class FeaturesModule

@Module
internal abstract class FakeSplashModule {
    @ContributesAndroidInjector
    abstract fun bindsActivity(): FakeSplashActivity
}