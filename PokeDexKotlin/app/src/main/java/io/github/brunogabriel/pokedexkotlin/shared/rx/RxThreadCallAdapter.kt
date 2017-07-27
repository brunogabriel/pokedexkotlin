package io.github.brunogabriel.pokedexkotlin.shared.rx

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import rx.Observable
import rx.Scheduler
import java.lang.reflect.Type

/**
 * Created by bruno on 7/25/17.
 */
class RxThreadCallAdapter(private val subscribeScheduler: Scheduler, private val observerScheduler: Scheduler) : CallAdapter.Factory() {

    internal var rxFactory = RxJavaCallAdapterFactory.create()

    override fun get(returnType: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): CallAdapter<*>?{
        val callAdapter = rxFactory.get(returnType, annotations, retrofit) as CallAdapter<Observable<*>>
        return if (callAdapter != null) ThreadCallAdapter(callAdapter) else null
    }


    internal inner class ThreadCallAdapter(var delegateAdapter: CallAdapter<Observable<*>>) : CallAdapter<Observable<*>> {
        override fun responseType(): Type {
            return delegateAdapter.responseType()
        }
        override fun <T> adapt(call: Call<T>): Observable<*> {
            return delegateAdapter.adapt(call).subscribeOn(subscribeScheduler)
                    .observeOn(observerScheduler)
        }
    }
}