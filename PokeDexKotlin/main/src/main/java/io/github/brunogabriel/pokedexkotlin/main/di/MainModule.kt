package io.github.brunogabriel.pokedexkotlin.main.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import io.github.brunogabriel.pokedexkotlin.main.presentation.activity.MainActivity
import io.github.brunogabriel.pokedexkotlin.main.presentation.viewmodel.MainViewModel
import io.github.brunogabriel.pokedexkotlin.shared.factory.ViewModelKey

@Module(
    includes = [
        MainActivityModule::class
    ]
)
abstract class MainModule

@Module(
    includes = [
        MainViewModelModule::class
    ]
)
internal abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract fun bindActivity(): MainActivity
}

@Module
internal abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindViewModel(viewModel: MainViewModel): ViewModel
}