package io.github.brunogabriel.pokedexkotlin.main.pokelist.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerFragment
import io.github.brunogabriel.pokedexkotlin.main.R
import io.github.brunogabriel.pokedexkotlin.main.pokelist.presentation.adapter.PokemonListAdapter
import io.github.brunogabriel.pokedexkotlin.main.pokelist.presentation.adapter.PokemonListDecoration
import io.github.brunogabriel.pokedexkotlin.main.pokelist.presentation.viewmodel.PokemonListViewModel
import io.github.brunogabriel.pokedexkotlin.shared.api.ApiResponse
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokeListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: PokemonListViewModel

    private lateinit var root: View

    private val adapter: PokemonListAdapter by lazy {
        PokemonListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_pokelist, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        listenToEvents()
        viewModel.loadData()
    }

    private fun setupView() {
        with(root.findViewById<RecyclerView>(R.id.recyclerView)) {
            adapter = this@PokeListFragment.adapter
            addItemDecoration(PokemonListDecoration())
        }
    }

    private fun listenToEvents() {
        lifecycleScope.launch {
            viewModel.pokemonsState.collect { state ->
                when (state) {
                    is ApiResponse.Success -> {
                        adapter.add(state.data)
                    }
                }
            }
        }
    }
}