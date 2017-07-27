package io.github.brunogabriel.pokedexkotlin.shared.network

import android.content.Context
import android.os.Build
import com.readystatesoftware.chuck.ChuckInterceptor
import io.github.brunogabriel.pokedexkotlin.BuildConfig
import io.github.brunogabriel.pokedexkotlin.shared.rx.RxThreadCallAdapter
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by bruno on 7/25/17.
 */
object RetrofitManager {
    private val READ_TIMEOUT = 60
    private val CONNECTION_TIMEOUT = 60

    private var context: Context? = null
    private var retrofit: Retrofit? = null
    private var okHttpClient: OkHttpClient? = null

    private fun setup(): Retrofit? {
        if (okHttpClient == null) {
            val okHttpBuilder = OkHttpClient.Builder()

            if (BuildConfig.DEBUG && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && context != null) {
                okHttpBuilder.addInterceptor(ChuckInterceptor(context))
            }

            okHttpClient = okHttpBuilder
                    .connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .build()

        }

        if (retrofit == null) {
            val builder = Retrofit.Builder()
                    .baseUrl(BuildConfig.base_url)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxThreadCallAdapter(Schedulers.io(), AndroidSchedulers.mainThread()))

            retrofit = builder.build()
        }

        return retrofit
    }

    fun injectContext(context: Context): RetrofitManager {
        this.context = context
        setup()
        return this
    }

    fun <T> createService(clazz: Class<T>): T? {
        return retrofit?.create(clazz)
    }
}