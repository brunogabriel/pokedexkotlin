package io.github.brunogabriel.pokedexkotlin.main.favorites

import io.github.brunogabriel.pokedexkotlin.shared.arch.BaseView
import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon

interface FavoritesContract {
    interface View : BaseView<Presenter> {
        fun showFavorites(pokemonList: List<Pokemon>)
        fun showEmptyFavorites()
    }

    interface Presenter {
        fun onViewWillAppear()
    }
}
