package io.github.brunogabriel.pokedexkotlin.main.favorites

import io.github.brunogabriel.pokedexkotlin.shared.arch.BaseView
import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon

/**
 * Created by brunogabriel on 2019-08-29.
 */
interface FavoritesContract {
    interface View : BaseView<Presenter> {
        fun showFavorites(pokemonList: List<Pokemon>)
        fun showEmptyFavorites()
    }

    interface Presenter {
        fun onViewWillAppear()
    }
}
