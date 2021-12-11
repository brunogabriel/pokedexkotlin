package io.github.brunogabriel.pokedexkotlin.main.base.presentation.activity

import android.os.Bundle
import android.widget.TextView
import dagger.android.support.DaggerAppCompatActivity
import io.github.brunogabriel.pokedexkotlin.main.R
import io.github.brunogabriel.pokedexkotlin.main.base.presentation.viewmodel.MainViewModel
import kotlinx.serialization.Serializable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}