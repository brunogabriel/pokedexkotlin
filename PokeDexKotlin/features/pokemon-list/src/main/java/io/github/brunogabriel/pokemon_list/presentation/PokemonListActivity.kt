package io.github.brunogabriel.pokemon_list.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import io.github.brunogabriel.core_app.ui.UiState
import io.github.brunogabriel.pokemon_list.R
import io.github.brunogabriel.pokemon_list.databinding.ActivityPokemonListBinding
import io.github.brunogabriel.pokemon_list.presentation.adapter.PokemonAdapter
import io.github.brunogabriel.style_guide.decorations.MarginItemDecoration
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by bruno on 27/02/20
 */
class PokemonListActivity : AppCompatActivity() {
    private val viewModel: PokemonListViewModel by viewModel()
    private val pokemonAdapter: PokemonAdapter by inject()
    private lateinit var binding: ActivityPokemonListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pokemon_list)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setupView()
    }

    private fun setupView() {
        setupToolbar()
        setupRecyclerView()
        setupViewModel()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun setupViewModel() {
        viewModel.fetchPokemons()
        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is UiState.Success -> {
                    binding.tryAgainView.visibility = View.GONE
                    binding.loadingView.visibility = View.GONE
                    pokemonAdapter.pokemonList = state.data
                    binding.pokemonsRecyclerView.visibility = View.VISIBLE
                }
                is UiState.Loading -> {
                    binding.tryAgainView.visibility = View.GONE
                    binding.loadingView.visibility = View.VISIBLE
                }
                is UiState.Failure -> {
                    binding.loadingView.visibility = View.GONE
                    binding.tryAgainView.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setupRecyclerView() {
        binding.pokemonsRecyclerView.apply {
            adapter = pokemonAdapter
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.margin_span_size),
                    (this.layoutManager as GridLayoutManager).spanCount
                )
            )
        }
    }
}
