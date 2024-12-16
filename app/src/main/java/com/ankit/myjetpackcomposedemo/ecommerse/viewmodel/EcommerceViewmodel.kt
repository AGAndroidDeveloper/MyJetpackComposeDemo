package com.ankit.myjetpackcomposedemo.ecommerse.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankit.myjetpackcomposedemo.ecommerse.model.ProductResponse
import com.ankit.myjetpackcomposedemo.ecommerse.network.Repository
import com.ankit.myjetpackcomposedemo.ecommerse.result.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EcommerceViewmodel() : ViewModel() {
    private var _productResponseState =
        MutableStateFlow<NetworkResult<ArrayList<ProductResponse>>>(NetworkResult.Idle)
    var productResponseState: StateFlow<NetworkResult<ArrayList<ProductResponse>>> =
        _productResponseState.asStateFlow()


    fun getProducts(repository: Repository) {
        viewModelScope.launch {
            _productResponseState.update {
                NetworkResult.Loading
            }

            _productResponseState.update {
                repository.getAllProduct()
            }
        }
    }

}