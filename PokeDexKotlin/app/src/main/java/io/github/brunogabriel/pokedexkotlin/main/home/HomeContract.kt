package io.github.brunogabriel.pokedexkotlin.main.home

import io.github.brunogabriel.pokedexkotlin.database.model.Pokemon
import io.github.brunogabriel.pokedexkotlin.shared.arch.BaseView
import io.github.brunogabriel.pokedexkotlin.shared.arch.LoadingView

/**
 * Created by brunogabriel on 2019-10-10.
 */
interface HomeContract {
    interface View : BaseView<Presenter>, LoadingView {
        fun showPokemons(pokemons: List<Pokemon>)
        fun showTryAgain()
    }

    interface Presenter {
        fun initialize()
        fun onClickAtTryAgain()
        fun onViewWillFinish()
        fun onFavoriteClicked(pokemon: Pokemon)
    }
}