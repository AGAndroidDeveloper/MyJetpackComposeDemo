package com.ankit.myjetpackcomposedemo.ecommerse.result

//sealed class NetworkResult<T>(
//    val data: T? = null, val message: String? = null
//) {
//    data object Idle : NetworkResult<Nothing>()
//    class Success<T>(data: T) : NetworkResult<T>(data)
//    class Error<T>(message: String?, data: T? = null) : NetworkResult<T>(data, message)
//    data object Loading : NetworkResult<Nothing>()
//}


sealed class NetworkResult<out T : Any> {
    data object Idle : NetworkResult<Nothing>()
    data object Loading : NetworkResult<Nothing>()
    data class Success<out T : Any>(val data: T) : NetworkResult<T>()
    data class Error(val exception: Throwable) : NetworkResult<Nothing>()
}