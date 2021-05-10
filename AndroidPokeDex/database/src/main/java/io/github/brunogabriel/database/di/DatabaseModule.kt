package io.github.brunogabriel.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.brunogabriel.database.app.PokemonDatabase
import io.github.brunogabriel.database.app.dao.PokemonDao

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val DATABASE_NAME = "pokemonDatabase.db"

    @Provides
    fun providesPokemonDatabase(
        @ApplicationContext context: Context
    ): PokemonDatabase =
        Room.databaseBuilder(context, PokemonDatabase::class.java, DATABASE_NAME).build()

    @Provides
    fun providesPokemonDao(
        pokemonDatabase: PokemonDatabase
    ): PokemonDao = pokemonDatabase.pokemonDao()
}