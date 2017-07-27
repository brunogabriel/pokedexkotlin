package io.github.brunogabriel.pokedexkotlin.shared.rx

import rx.Observable
import rx.Subscriber
import rx.functions.Action0
import java.net.UnknownHostException

/**
 * Created by bruno on 7/26/17.
 */
class UnknownHostOperator<T>(private val unknownAction: Action0) : Observable.Operator<T, T> {
    override fun call(subscriber: Subscriber<in T>): Subscriber<in T> {
        return object : Subscriber<T>() {
            override fun onCompleted() {
                if (!subscriber.isUnsubscribed) {
                    subscriber.onCompleted()
                }
            }

            override fun onError(throwable: Throwable) {
                if (!subscriber.isUnsubscribed) {
                    if (throwable is UnknownHostException) {
                        unknownAction.call()
                        subscriber.onCompleted()
                    } else {
                        subscriber.onError(throwable)
                    }
                }
            }

            override fun onNext(t: T) {
                if (!subscriber.isUnsubscribed) {
                    subscriber.onNext(t)
                }
            }
        }
    }

    companion object {
        fun <T> getUnknownHostOperator(unknownAction: Action0?): UnknownHostOperator<T>? {
            if (unknownAction == null) {
                return null
            }
            return UnknownHostOperator(unknownAction)
        }
    }
}