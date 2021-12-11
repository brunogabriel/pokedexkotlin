package io.github.brunogabriel.pokedexkotlin.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {
    companion object {
        @Singleton
        @Provides
        fun providesApplicationContext(application: Application): Context = application
    }
}