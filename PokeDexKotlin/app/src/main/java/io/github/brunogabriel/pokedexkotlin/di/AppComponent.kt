package io.github.brunogabriel.pokedexkotlin.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.github.brunogabriel.pokedexkotlin.PokeDexApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        FeaturesModule::class
    ]
)
interface AppComponent : AndroidInjector<PokeDexApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}