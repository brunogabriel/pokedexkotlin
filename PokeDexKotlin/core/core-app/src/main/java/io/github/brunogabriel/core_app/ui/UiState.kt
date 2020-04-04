package io.github.brunogabriel.core_app.ui

/**
 * Created by bruno on 28/02/20
 */
sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Failure(val throwable: Throwable) : UiState<Nothing>()
}
