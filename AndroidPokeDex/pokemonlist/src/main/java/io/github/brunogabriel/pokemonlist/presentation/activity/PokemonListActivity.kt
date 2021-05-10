package io.github.brunogabriel.pokemonlist.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.github.brunogabriel.pokemonlist.R
import io.github.brunogabriel.pokemonlist.databinding.ActivityPokemonListBinding
import io.github.brunogabriel.pokemonlist.presentation.viewmodel.PokemonListViewModel
import io.github.brunogabriel.shared.extensions.bind

@AndroidEntryPoint
class PokemonListActivity : AppCompatActivity() {

    private val viewModel: PokemonListViewModel by viewModels()

    private lateinit var binding: ActivityPokemonListBinding

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, PokemonListActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bind(R.layout.activity_pokemon_list) {
            viewmodel = viewModel
        }
        setContentView(R.layout.activity_pokemon_list)
    }
}