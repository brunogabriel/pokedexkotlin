package io.github.brunogabriel.pokedexkotlin.main.home

import io.github.brunogabriel.pokedexkotlin.shared.arch.BaseView
import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon

interface HomeContract {
    interface View : BaseView<Presenter> {
        fun showPokemons(pokemons: List<Pokemon>)
    }

    interface Presenter {
        fun initialize()
    }
}
