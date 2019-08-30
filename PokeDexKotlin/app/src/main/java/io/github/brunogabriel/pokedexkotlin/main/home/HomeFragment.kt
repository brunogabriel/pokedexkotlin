package io.github.brunogabriel.pokedexkotlin.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import io.github.brunogabriel.pokedexkotlin.R
import io.github.brunogabriel.pokedexkotlin.shared.adapter.PokemonListAdapter
import io.github.brunogabriel.pokedexkotlin.shared.extensions.toDP
import io.github.brunogabriel.pokedexkotlin.shared.model.PokemonResumed
import io.github.brunogabriel.pokedexkotlin.shared.view.PokemonCardItemDecoration
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by brunogabriel on 2019-08-29.
 */
class HomeFragment : Fragment(), HomeContract.View {


    override lateinit var presenter: HomeContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showPokemons((0..10).map { PokemonResumed("Charmander", "https://pokeapi.co/api/v2/pokemon/$it/") })
    }

    override fun showPokemons(pokemons: List<PokemonResumed>) {
        recycler_view_pokemon_list.apply {
            adapter = PokemonListAdapter(pokemons)
            addItemDecoration(PokemonCardItemDecoration(8.toDP(), (layoutManager as GridLayoutManager).spanCount))
        }
    }

}