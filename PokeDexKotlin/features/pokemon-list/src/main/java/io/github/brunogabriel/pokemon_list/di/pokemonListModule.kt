package io.github.brunogabriel.pokemon_list.di

import io.github.brunogabriel.pokemon_list.presentation.PokemonListViewModel
import io.github.brunogabriel.pokemon_list.presentation.adapter.PokemonAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by bruno on 28/02/20
 */
val pokemonListModule = module {
    factory { PokemonAdapter() }
    viewModel {
        PokemonListViewModel(
            fetchPokemonUseCases = get(),
            uiScheduler = AndroidSchedulers.mainThread()
        )
    }
}
