package com.ankit.myjetpackcomposedemo.ecommerse.network

import com.ankit.myjetpackcomposedemo.ecommerse.NetworkResult
import com.ankit.myjetpackcomposedemo.ecommerse.model.ProductResponse

class Repository(private val service: ApiService) {

    fun getAllProduct(): NetworkResult<ProductResponse> {
        val response = service.getAllProduct()
        val data = response.body()
        return try {
            if (response.isSuccessful) {
                if (data?.data != null) {
                    NetworkResult.Success(data = data.data)
                } else {
                    NetworkResult.Error(message = "${response.message()} code ${response.code()}")
                }
            } else {
                NetworkResult.Error(message = "${response.errorBody()} code ${response.code()}")
            }
        } catch (e: Exception) {
            NetworkResult.Error(message = "${e.localizedMessage} code ${response.code()}")
        }

    }
}