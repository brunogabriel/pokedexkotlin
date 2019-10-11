package io.github.brunogabriel.pokedexkotlin.shared.arch

import android.app.Application
import io.github.brunogabriel.pokedexkotlin.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by brunogabriel on 2019-10-10.
 */
class PokedexApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PokedexApplication)
            modules(
                listOf(databaseModule, networkModule, repositoryModule) + presenterModules
            )
        }
    }
}