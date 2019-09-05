package io.github.brunogabriel.pokedexkotlin.details

import io.github.brunogabriel.pokedexkotlin.shared.arch.BaseView
import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon

/**
 * Created by brunogabriel on 2019-09-04.
 */
interface PokemonDetailsContract {
    interface View : BaseView<Presenter> {
        fun showPokemonResume(number: Long, name: String?, imageUrl: String)
        fun showPokemonDetails(pokemon: Pokemon)
        fun showLoading()
        fun dismissLoading()
        fun showErrorGettingPokemonDetails()
    }

    interface Presenter {
        fun initialize()
        fun onDestroyView()
    }
}
