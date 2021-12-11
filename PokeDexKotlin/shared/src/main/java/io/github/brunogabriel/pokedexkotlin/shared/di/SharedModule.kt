package io.github.brunogabriel.pokedexkotlin.shared.di

import dagger.Module
import dagger.Provides
import io.github.brunogabriel.pokedexkotlin.shared.coroutines.AppDispatchers
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module(
    includes = [
        AppDispatchersModule::class
    ]
)
abstract class SharedModule

@Module
internal class AppDispatchersModule {
    @Singleton
    @Provides
    fun appDispatchers() = AppDispatchers(
        default = Dispatchers.Default,
        io = Dispatchers.IO,
        main = Dispatchers.Main
    )
}