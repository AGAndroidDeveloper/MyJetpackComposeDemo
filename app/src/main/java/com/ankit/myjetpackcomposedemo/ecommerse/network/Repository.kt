package com.ankit.myjetpackcomposedemo.ecommerse.network

import com.ankit.myjetpackcomposedemo.ecommerse.result.NetworkResult
import com.ankit.myjetpackcomposedemo.ecommerse.model.ProductResponse

class Repository(private val service: ApiService) {


    suspend fun getAllProduct(): NetworkResult<ArrayList<ProductResponse>> {
        val response = service.getAllProduct()
        val data = response.body()
        return try {
            if (response.isSuccessful) {
                if (data != null) {
                    NetworkResult.Success(data = data)
                } else {
                    val errorMessage =
                        Throwable(message = "${response.message()} code ${response.code()}")
                    NetworkResult.Error(errorMessage)
                }
            } else {
                val errorMessage =
                    Throwable(message = "${response.errorBody()} code ${response.code()}")
                NetworkResult.Error(errorMessage)
            }
        } catch (e: Exception) {
            val message = Throwable("${response.errorBody()} code ${response.code()}")
            NetworkResult.Error(message)
        }

    }

}