package com.demo.mvvmcomposehilt.data.common

sealed class Resource<out T> {
    data class Success<out T>(val response: T) : Resource<T>()
    data class Error(val exception: Exception) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
}

inline fun <T : Any> Resource<T>.onSuccess(action: (T) -> Unit): Resource<T> {
    if (this is Resource.Success) action(response)
    return this
}

inline fun <T : Any> Resource<T>.onError(action: (Exception) -> Unit): Resource<T> {
    if (this is Resource.Error) action(exception)
    return this
}

inline fun <T : Any> Resource<T>.onLoading(action: () -> Unit): Resource<T> {
    if (this is Resource.Loading) action()
    return this
}
