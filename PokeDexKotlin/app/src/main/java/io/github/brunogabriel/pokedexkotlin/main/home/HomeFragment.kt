package io.github.brunogabriel.pokedexkotlin.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import io.github.brunogabriel.pokedexkotlin.R
import io.github.brunogabriel.pokedexkotlin.details.PokemonDetailsActivity
import io.github.brunogabriel.pokedexkotlin.details.PokemonDetailsActivity.Companion.POKEMON_NUMBER
import io.github.brunogabriel.pokedexkotlin.shared.adapter.PokemonGridAdapter
import io.github.brunogabriel.pokedexkotlin.shared.extensions.toDP
import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon
import io.github.brunogabriel.pokedexkotlin.shared.operation.PokemonOperations
import io.github.brunogabriel.pokedexkotlin.shared.view.ColumnSpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_empty.*
import kotlinx.android.synthetic.main.view_error_try_again.*
import kotlinx.android.synthetic.main.view_loading.*

/**
 * Created by brunogabriel on 2019-08-29.
 */
class HomeFragment : Fragment(), HomeContract.View {

    override lateinit var presenter: HomeContract.Presenter
    private val pokemonListAdapter = PokemonGridAdapter({ pokemon, _ ->
        startActivity(
            Intent(activity, PokemonDetailsActivity::class.java).putExtra(
                POKEMON_NUMBER, pokemon.number
            )
        )
    }, { pokemon, position ->
        presenter.onPokemonFavoriteAction(pokemon, position)
    })

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
            addItemDecoration(
                ColumnSpaceItemDecoration(
                    8.toDP(),
                    (layoutManager as GridLayoutManager).spanCount
                )
            )
        }
        presenter = HomePresenter(this, PokemonOperations()).apply {
            initialize()
        }
    }

    override fun showPokemonList(pokemonList: List<Pokemon>) {
        pokemonListAdapter.insertItems(pokemonList)
    }

    override fun showLoading() {
        view_loading.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        view_loading.visibility = View.GONE
    }

    override fun showEmptyList() {
        view_empty.visibility = View.VISIBLE
    }

    override fun showError() {
        view_error_try_again.apply {
            visibility = View.VISIBLE
            try_again_button.setOnClickListener {
                view_error_try_again.visibility = View.GONE
                presenter.initialize()
            }
        }
    }

    override fun updatePokemonAtPosition(pokemon: Pokemon, position: Int) {
        pokemonListAdapter.updateItemAtPosition(pokemon, position)
    }

    override fun onDestroyView() {
        presenter.onDestroyView()
        super.onDestroyView()
    }
}