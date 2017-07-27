package io.github.brunogabriel.pokedexkotlin.shared.application

import android.app.Application
import io.github.brunogabriel.pokedexkotlin.R
import io.github.brunogabriel.pokedexkotlin.shared.network.RetrofitManager
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

/**
 * Created by bruno on 7/25/17.
 */
class PokeDexKotlinApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        RetrofitManager.injectContext(this)
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("Raleway/Raleway-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build())
    }
}