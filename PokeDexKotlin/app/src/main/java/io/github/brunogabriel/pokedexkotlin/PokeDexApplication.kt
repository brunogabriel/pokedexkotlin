package io.github.brunogabriel.pokedexkotlin

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by bruno on 2020-02-26
 */
class PokeDexApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PokeDexApplication)
        }
    }
}