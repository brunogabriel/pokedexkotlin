package io.github.brunogabriel.pokedexkotlin.details

import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon
import io.github.brunogabriel.pokedexkotlin.shared.operation.PokemonOperations
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by brunogabriel on 2019-09-04.
 */
class PokemonDetailsPresenter(private val view: PokemonDetailsContract.View,
                              private val pokemonNumber: Long,
                              private val pokemonOperations: PokemonOperations = PokemonOperations(),
                              private val subscriberScheduler: Scheduler = Schedulers.io(),
                              private val observerScheduler: Scheduler = AndroidSchedulers.mainThread()
) : PokemonDetailsContract.Presenter {

    // TODO: pokemon hasPokemonChanged need to back to previous activity

    private lateinit var pokemon: Pokemon
    private val compositeDisposable = CompositeDisposable()

    override fun initialize() {
        pokemon = pokemonOperations.findPokemon(pokemonNumber)!!
        view.showPokemonResume(pokemon.number!!, pokemon.name, pokemon.findSpriteUrl())
        compositeDisposable.add(pokemonOperations.findPokemonDetails(pokemonNumber)
            .subscribeOn(subscriberScheduler)
            .observeOn(observerScheduler)
            .doOnSubscribe { view.showLoading() }
            .doAfterTerminate { view.dismissLoading() }
            .subscribe({
                pokemon = it
                view.showPokemonDetails(pokemon)
            }, {
                view.showErrorGettingPokemonDetails()
            }))
    }

    override fun onDestroyView() {
        compositeDisposable.dispose()
    }
}