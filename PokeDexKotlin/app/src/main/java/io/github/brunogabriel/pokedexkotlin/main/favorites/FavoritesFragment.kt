package io.github.brunogabriel.pokedexkotlin.main.favorites

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.brunogabriel.pokedexkotlin.R
import io.github.brunogabriel.pokedexkotlin.details.PokemonDetailsActivity
import io.github.brunogabriel.pokedexkotlin.shared.adapter.PokemonListAdapter
import io.github.brunogabriel.pokedexkotlin.shared.extensions.toDP
import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon
import io.github.brunogabriel.pokedexkotlin.shared.view.ColumnSpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.view_empty_favorites.*

/**
 * Created by brunogabriel on 2019-08-29.
 */
class FavoritesFragment : Fragment(), FavoritesContract.View {
    override lateinit var presenter: FavoritesContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view_pokemon.addItemDecoration(ColumnSpaceItemDecoration(8.toDP(), 1))
        presenter = FavoritesPresenter(this)
    }

    override fun showFavorites(pokemonList: List<Pokemon>) {
        view_favorites.visibility = View.GONE
        recycler_view_pokemon.apply {
            visibility = View.VISIBLE
            adapter = PokemonListAdapter(pokemonList) { pokemon,_ ->
                startActivity(
                    Intent(activity, PokemonDetailsActivity::class.java).putExtra(
                        PokemonDetailsActivity.POKEMON_NUMBER, pokemon.number))
            }
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            presenter.onViewWillAppear()
        }
    }
    override fun showEmptyFavorites() {
        view_favorites.visibility = View.VISIBLE
        recycler_view_pokemon.visibility = View.GONE
    }
}