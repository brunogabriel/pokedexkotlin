package io.github.brunogabriel.pokedexkotlin.main.home

import io.github.brunogabriel.pokedexkotlin.shared.arch.BaseView
import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon

interface HomeContract {
    interface View : BaseView<Presenter> {
        fun showPokemonList(pokemonList: List<Pokemon>)
        fun showLoading()
        fun dismissLoading()
        fun showEmptyList()
        fun showError()
    }

    interface Presenter {
        fun initialize()
        fun onDestroyView()
    }
}
