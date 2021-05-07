package io.github.brunogabriel.androidpokedex.application.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.brunogabriel.shared.rx.ApplicationSchedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideApplicationSchedulers(): ApplicationSchedulers = ApplicationSchedulers(
        ioScheduler = Schedulers.io(),
        mainScheduler = AndroidSchedulers.mainThread(),
        computationScheduler = Schedulers.computation()
    )
}