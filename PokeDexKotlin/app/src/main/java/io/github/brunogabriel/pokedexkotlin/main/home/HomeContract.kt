package io.github.brunogabriel.pokedexkotlin.main.home

import io.github.brunogabriel.pokedexkotlin.database.model.Pokemon
import io.github.brunogabriel.pokedexkotlin.shared.arch.BaseView

/**
 * Created by brunogabriel on 2019-10-10.
 */
interface HomeContract {
    interface View : BaseView<Presenter> {
        fun showPokemons(pokemons: List<Pokemon>)
    }

    interface Presenter {
        fun initialize()
        fun onClickAtTryAgain()
        fun onViewWillFinish()
    }
}