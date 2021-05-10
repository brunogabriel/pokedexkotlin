package io.github.brunogabriel.pokemonlist.di

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import io.github.brunogabriel.pokemonlist.presentation.viewmodel.PokemonListViewModel

@Module
@InstallIn(ActivityComponent::class)
object PokemonListModule {
    @Provides
    fun providesPokemonListViewModel(): ViewModel = PokemonListViewModel()
}