package io.github.brunogabriel.pokedexkotlin.main.base.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import io.github.brunogabriel.pokedexkotlin.main.base.presentation.activity.MainActivity
import io.github.brunogabriel.pokedexkotlin.main.base.presentation.viewmodel.MainViewModel
import io.github.brunogabriel.pokedexkotlin.main.pokelist.di.PokeListModule
import io.github.brunogabriel.pokedexkotlin.shared.factory.ViewModelKey

@Module(
    includes = [
        MainViewModelModule::class,
    ]
)
internal abstract class MainActivityModule {
    @ContributesAndroidInjector(
        modules = [
            PokeListModule::class
        ]
    )
    abstract fun bindActivity(): MainActivity
}

@Module
internal abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindViewModel(viewModel: MainViewModel): ViewModel
}