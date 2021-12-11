package io.github.brunogabriel.pokedexkotlin

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.github.brunogabriel.pokedexkotlin.di.DaggerAppComponent

class PokeDexApplication : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}