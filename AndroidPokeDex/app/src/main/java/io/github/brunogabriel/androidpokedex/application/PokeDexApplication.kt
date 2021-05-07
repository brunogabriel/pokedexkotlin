package io.github.brunogabriel.androidpokedex.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PokeDexApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}