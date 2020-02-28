package io.github.brunogabriel.core_app.ui

import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer

/**
 * Created by bruno on 28/02/20
 */
class UiStateSingle<T> : SingleTransformer<T, UiState<T>> {
    override fun apply(upstream: Single<T>): SingleSource<UiState<T>> {
        return upstream.map {
            UiState.Success(it) as UiState<T>
        }.onErrorReturn {
            UiState.Failure(it)
        }.doOnSubscribe {
            UiState.Loading
        }
    }
}
