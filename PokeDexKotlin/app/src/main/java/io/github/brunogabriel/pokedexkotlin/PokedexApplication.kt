package io.github.brunogabriel.pokedexkotlin

import android.app.Application
import io.github.brunogabriel.data_core.di.dataModule
import io.github.brunogabriel.data_local.di.dataLocalModule
import io.github.brunogabriel.data_remote.di.dataRemoteModule
import io.github.brunogabriel.domain.di.domainModule
import io.github.brunogabriel.pokemon_list.di.pokemonListModule
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
                    dataModule, dataLocalModule, dataRemoteModule,
                    domainModule,
                    pokemonListModule
                )
            )
        }
    }
}
