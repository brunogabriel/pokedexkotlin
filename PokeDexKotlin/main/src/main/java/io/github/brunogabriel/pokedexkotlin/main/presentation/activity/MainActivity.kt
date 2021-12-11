package io.github.brunogabriel.pokedexkotlin.main.presentation.activity

import android.os.Bundle
import android.widget.TextView
import dagger.android.support.DaggerAppCompatActivity
import io.github.brunogabriel.pokedexkotlin.main.R
import io.github.brunogabriel.pokedexkotlin.main.presentation.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.mainText).text = viewModel.getText()
    }
}