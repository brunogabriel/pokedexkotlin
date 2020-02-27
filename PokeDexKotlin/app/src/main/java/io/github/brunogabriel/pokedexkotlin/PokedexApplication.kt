package io.github.brunogabriel.pokedexkotlin

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by bruno on 27/02/20
 */
class PokedexApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PokedexApplication)
            modules(
                listOf(
                    // TODO:
                )
            )
        }
    }
}