package io.github.brunogabriel.pokedexkotlin.main.pokelist.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import io.github.brunogabriel.pokedexkotlin.main.R
import io.github.brunogabriel.pokedexkotlin.main.pokelist.presentation.viewmodel.PokemonListViewModel
import javax.inject.Inject

class PokeListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: PokemonListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokelist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadData()
    }
}