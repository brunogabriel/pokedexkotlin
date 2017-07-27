package io.github.brunogabriel.pokedexkotlin.main

import io.github.brunogabriel.pokedexkotlin.shared.application.BasePresenter
import io.github.brunogabriel.pokedexkotlin.shared.application.BaseView

/**
 * Created by bruno on 7/25/17.
 */
interface MainContract {

    interface View : BaseView<Presenter> {
        fun startView()
    }

    interface Presenter : BasePresenter {
    }
}