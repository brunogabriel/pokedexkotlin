package io.github.brunogabriel.pokedexkotlin.main.presentation

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import io.github.brunogabriel.pokedexkotlin.main.R

class MainActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}