package io.github.brunogabriel.core_app.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by bruno on 27/02/20
 */
open class BaseViewModel : ViewModel() {
    private val disposables = CompositeDisposable()
    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}
