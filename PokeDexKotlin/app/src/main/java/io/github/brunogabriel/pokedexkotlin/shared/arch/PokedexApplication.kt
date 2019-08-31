package io.github.brunogabriel.pokedexkotlin.shared.arch

import android.app.Application
import io.github.brunogabriel.pokedexkotlin.shared.networking.RetrofitManager

/**
 * Created by brunogabriel on 2019-08-31.
 */
class PokedexApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RetrofitManager.initialize(this)

        // TODO: realm database start
    }
}