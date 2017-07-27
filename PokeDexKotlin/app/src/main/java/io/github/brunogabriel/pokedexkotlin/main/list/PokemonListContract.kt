package io.github.brunogabriel.pokedexkotlin.main.list

import io.github.brunogabriel.pokedexkotlin.shared.application.BasePresenter
import io.github.brunogabriel.pokedexkotlin.shared.application.BaseView
import io.github.brunogabriel.pokedexkotlin.shared.application.LoadingView
import io.github.brunogabriel.pokedexkotlin.shared.models.Pokemon

/**
 * Created by bruno on 7/26/17.
 */
interface PokemonListContract {

    interface View: BaseView<Presenter>, LoadingView {
        fun showNetworkUnavailable()
        fun showPokemonResource(pokemonResourceList: List<Pokemon>)
        fun showTryAgain()
    }

    interface Presenter: BasePresenter
}