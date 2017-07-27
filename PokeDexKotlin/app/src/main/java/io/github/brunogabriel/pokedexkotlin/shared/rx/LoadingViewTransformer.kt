package io.github.brunogabriel.pokedexkotlin.shared.rx

import io.github.brunogabriel.pokedexkotlin.shared.application.LoadingView
import rx.Observable

/**
 * Created by bruno on 7/26/17.
 */
class LoadingViewTransformer<T>(private val loadingView: LoadingView) : Observable.Transformer<T, T> {
    override fun call(observable: Observable<T>?): Observable<T> {
        return observable
                ?.doOnSubscribe(loadingView::showLoading)
                ?.doOnTerminate(loadingView::dismissLoading)!!
    }
}