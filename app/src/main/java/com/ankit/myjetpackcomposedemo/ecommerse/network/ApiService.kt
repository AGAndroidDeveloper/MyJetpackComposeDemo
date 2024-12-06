package com.ankit.myjetpackcomposedemo.ecommerse.network

import com.ankit.myjetpackcomposedemo.ecommerse.NetworkResult
import com.ankit.myjetpackcomposedemo.ecommerse.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/products")
    fun getAllProduct(): Response<NetworkResult<ProductResponse>>
}