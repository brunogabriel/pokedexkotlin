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
import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon
import io.github.brunogabriel.pokedexkotlin.shared.networking.PokemonService
import io.github.brunogabriel.pokedexkotlin.shared.networking.RetrofitManager
import io.github.brunogabriel.pokedexkotlin.shared.view.ColumnSpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by brunogabriel on 2019-08-29.
 */
class HomeFragment : Fragment(), HomeContract.View {

    override lateinit var presenter: HomeContract.Presenter
    private val pokemonListAdapter = PokemonListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view_pokemon_list.apply {
            adapter = pokemonListAdapter
            addItemDecoration(ColumnSpaceItemDecoration(8.toDP(),(layoutManager as GridLayoutManager).spanCount))
        }
        presenter = HomePresenter(this,
            RetrofitManager.createService(PokemonService::class.java)).apply {
            initialize()
        }
    }

    override fun showPokemons(pokemons: List<Pokemon>) {
        pokemonListAdapter.insertItems(pokemons)
    }
}