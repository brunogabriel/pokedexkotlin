package io.github.brunogabriel.pokedexkotlin.shared.api

sealed class ApiResponse<out T> {
    object None : ApiResponse<Nothing>()
    object Loading : ApiResponse<Nothing>()
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error(val throwable: Throwable) : ApiResponse<Nothing>()
}