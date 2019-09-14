package io.github.brunogabriel.pokedexkotlin.shared.networking

import android.content.Context
import android.util.Log
import com.readystatesoftware.chuck.ChuckInterceptor
import io.github.brunogabriel.pokedexkotlin.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by brunogabriel on 2019-08-29.
 */
object RetrofitManager {
    private const val READ_TIMEOUT = 120L
    private const val CONNECT_TIMEOUT = 60L
    private lateinit var retrofit: Retrofit
    private lateinit var okHttpClient: OkHttpClient

    fun initialize(context: Context, baseUrl: String = BuildConfig.BASE_URL) {
        if (!::retrofit.isInitialized) {
            okHttpClient = OkHttpClient.Builder()
                .apply {
                    try {
                        addInterceptor(ChuckInterceptor(context))
                    } catch (exception: Exception) {
                        Log.d(RetrofitManager::class.java.name, "Fail using context adding interceptor")
                    }
                }
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .build()

            retrofit = Retrofit
                .Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    fun changeBaseUrl(baseUrl: String) {
        retrofit = Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> createService(serviceClazz: Class<T>): T = retrofit.create(serviceClazz)
}