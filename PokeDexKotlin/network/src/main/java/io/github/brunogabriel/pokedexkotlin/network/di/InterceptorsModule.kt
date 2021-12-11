package io.github.brunogabriel.pokedexkotlin.network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import io.github.brunogabriel.pokedexkotlin.network.BuildConfig
import io.github.brunogabriel.pokedexkotlin.network.di.qualifiers.Chucker
import io.github.brunogabriel.pokedexkotlin.network.di.qualifiers.Logging
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
internal abstract class InterceptorsModule {
    companion object {
        @Singleton
        @Provides
        @Chucker
        fun providesChuckerInterceptor(context: Context): Interceptor =
            ChuckerInterceptor.Builder(context).build()

        @Singleton
        @Provides
        @Logging
        fun providesLoggingInterceptor(): Interceptor =
            HttpLoggingInterceptor().apply {
                level = if (BuildConfig.BUILD_TYPE == "release")
                    HttpLoggingInterceptor.Level.NONE else HttpLoggingInterceptor.Level.BODY
            }
    }
}