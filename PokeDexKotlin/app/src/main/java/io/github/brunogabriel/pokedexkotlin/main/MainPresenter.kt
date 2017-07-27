package io.github.brunogabriel.pokedexkotlin.main

/**
 * Created by bruno on 7/25/17.
 */
class MainPresenter(val view: MainContract.View) : MainContract.Presenter {

    override fun start() {
        view.startView()
    }

}