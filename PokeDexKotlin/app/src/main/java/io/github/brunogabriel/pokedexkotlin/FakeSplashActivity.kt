package io.github.brunogabriel.pokedexkotlin

import android.content.Intent
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import io.github.brunogabriel.pokedexkotlin.main.presentation.MainActivity

class FakeSplashActivity : DaggerAppCompatActivity() {
    // TODO: Remove it in future
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Temporary use deeplink
        startActivity(Intent(this, MainActivity::class.java))
    }
}