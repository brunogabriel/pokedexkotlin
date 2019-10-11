package io.github.brunogabriel.pokedexkotlin.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import io.github.brunogabriel.pokedexkotlin.R
import io.github.brunogabriel.pokedexkotlin.database.model.Pokemon
import io.github.brunogabriel.pokedexkotlin.shared.adapter.PokemonGridAdapter
import io.github.brunogabriel.pokedexkotlin.shared.view.ColumnSpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * Created by brunogabriel on 2019-10-10.
 */
class HomeFragment : Fragment(), HomeContract.View {
    override val presenter: HomeContract.Presenter by inject { parametersOf(this) }
    private val pokemonGridAdapter: PokemonGridAdapter by lazy { PokemonGridAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        presenter.initialize()
    }

    override fun showPokemons(pokemons: List<Pokemon>) {
        pokemonGridAdapter.setPokemons(pokemons)
    }

    private fun setupView() {
        pokemon_list_recycler_view.apply {
            adapter = pokemonGridAdapter
            addItemDecoration(
                ColumnSpaceItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.grid_dimen),
                    (layoutManager as GridLayoutManager).spanCount
                )
            )
        }
    }
}