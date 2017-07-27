package io.github.brunogabriel.pokedexkotlin.main.list

import io.github.brunogabriel.pokedexkotlin.shared.network.service.PokemonService
import io.github.brunogabriel.pokedexkotlin.shared.rx.LoadingViewTransformer
import io.github.brunogabriel.pokedexkotlin.shared.rx.UnknownHostOperator
import rx.functions.Action0

/**
 * Created by bruno on 7/26/17.
 */
class PokemonListPresenter(val view: PokemonListContract.View, val service: PokemonService) : PokemonListContract.Presenter {
    override fun start() {
        service.getPokemonResourceSummary()
                .lift(UnknownHostOperator.getUnknownHostOperator(Action0 {
                    view.showNetworkUnavailable()
                }))
                .compose(LoadingViewTransformer(view))
                .subscribe({
                    view.showPokemonResource(it.results!!)
                }, {
                    _ -> view.showTryAgain()
                })
    }

}