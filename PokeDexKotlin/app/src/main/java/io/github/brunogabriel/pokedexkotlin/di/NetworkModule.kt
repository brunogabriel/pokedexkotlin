package io.github.brunogabriel.pokedexkotlin.di

import androidx.annotation.VisibleForTesting
import com.chuckerteam.chucker.api.ChuckerInterceptor
import io.github.brunogabriel.pokedexkotlin.BuildConfig
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by brunogabriel on 2019-10-10.
 */
@VisibleForTesting
var urlTest: String? = null

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .readTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(40L, TimeUnit.SECONDS)
            .addInterceptor(ChuckerInterceptor(get()))
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(urlTest ?: BuildConfig.API_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}