package io.github.brunogabriel.pokedexkotlin.main.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import io.github.brunogabriel.pokedexkotlin.R
import io.github.brunogabriel.pokedexkotlin.database.model.Pokemon
import io.github.brunogabriel.pokedexkotlin.shared.adapter.PokemonGridAdapter
import io.github.brunogabriel.pokedexkotlin.shared.view.ColumnSpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.request_fail_view.view.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * Created by brunogabriel on 2019-10-10.
 */
class HomeFragment : Fragment(), HomeContract.View {
    override val presenter: HomeContract.Presenter by inject { parametersOf(this) }
    private val pokemonGridAdapter: PokemonGridAdapter by lazy {
        PokemonGridAdapter {
            presenter.onFavoriteClicked(it)
        }
    }

    private var searchMenu: MenuItem? = null

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
        searchMenu = menu.findItem(R.id.action_search)
        searchMenu?.isVisible = false

        // TODO: apply query rules
        searchMenu?.actionView as SearchView
    }

    override fun showPokemons(pokemons: List<Pokemon>) {
        searchMenu?.isVisible = true
        empty_pokemon_result_view.visibility = View.GONE
        pokemonGridAdapter.setPokemons(pokemons)
    }

    override fun showTryAgain() {
        request_fail_view.apply {
            visibility = View.VISIBLE
            try_again_button.setOnClickListener {
                visibility = View.GONE
                presenter.initialize()
            }
        }
    }

    override fun showLoading() {
        loading_view.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        loading_view.visibility = View.GONE
    }

    override fun showEmpyResult() {
        empty_pokemon_result_view.visibility = View.VISIBLE
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