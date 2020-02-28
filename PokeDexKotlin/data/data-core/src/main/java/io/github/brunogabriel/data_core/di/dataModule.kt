package io.github.brunogabriel.data_core.di

import io.github.brunogabriel.data_core.repository.PokemonRepositoryImplementation
import io.github.brunogabriel.domain.repository.PokemonRepository
import org.koin.dsl.module

/**
 * Created by bruno on 28/02/20
 */
val dataModule = module {
    factory<PokemonRepository> {
        PokemonRepositoryImplementation(
            cacheDataSource = get(),
            remoteDataSource = get()
        )
    }
}