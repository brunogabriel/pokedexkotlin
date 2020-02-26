package io.github.brunogabriel.pokemonlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import io.github.brunogabriel.pokemonlist.databinding.ActivityPokemonMainBinding

/**
 * Created by bruno on 2020-02-26
 */
class PokemonMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPokemonMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pokemon_main)
        setupView()
    }

    private fun setupView() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""
    }
}
