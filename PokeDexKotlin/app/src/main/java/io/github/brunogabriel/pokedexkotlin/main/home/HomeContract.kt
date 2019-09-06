package io.github.brunogabriel.pokedexkotlin.main.home

import io.github.brunogabriel.pokedexkotlin.shared.arch.BaseView
import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon

/**
 * Created by brunogabriel on 2019-08-29.
 */
interface HomeContract {
    interface View : BaseView<Presenter> {
        fun showPokemonList(pokemonList: List<Pokemon>)
        fun showLoading()
        fun dismissLoading()
        fun showEmptyList()
        fun showError()
        fun updatePokemonAtPosition(pokemon: Pokemon, position: Int)
    }

    interface Presenter {
        fun initialize()
        fun onDestroyView()
        fun onPokemonFavoriteAction(pokemon: Pokemon, position: Int)
    }
}
