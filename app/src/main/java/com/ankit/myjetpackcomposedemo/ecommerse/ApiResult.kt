package com.ankit.myjetpackcomposedemo.ecommerse

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Idle<T> : NetworkResult<T>()
    class Success<T>(data: T) : NetworkResult<T>(data)

    class Error<T>(message: String?, data: T? = null) : NetworkResult<T>(data, message)

    class Loading<T> : NetworkResult<T>()

}