package io.github.brunogabriel.pokedexkotlin.di

import io.github.brunogabriel.pokedexkotlin.main.home.HomeContract
import io.github.brunogabriel.pokedexkotlin.main.home.HomePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module

/**
 * Created by brunogabriel on 2019-10-10.
 */
private val homeModule = module {
    factory<HomeContract.Presenter> { (view: HomeContract.View) ->
        HomePresenter(
            view, get(), Schedulers.io(),
            AndroidSchedulers.mainThread()
        )
    }
}

val presenterModules = listOf(homeModule)