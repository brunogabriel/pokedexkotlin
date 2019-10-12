package io.github.brunogabriel.pokedexkotlin.shared.arch

import android.app.Application
import com.facebook.stetho.Stetho
import io.github.brunogabriel.pokedexkotlin.BuildConfig
import io.github.brunogabriel.pokedexkotlin.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by brunogabriel on 2019-10-10.
 */
class PokedexApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
        startKoin {
            androidContext(this@PokedexApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    operationsModule
                ) + presenterModules
            )
        }
    }
}