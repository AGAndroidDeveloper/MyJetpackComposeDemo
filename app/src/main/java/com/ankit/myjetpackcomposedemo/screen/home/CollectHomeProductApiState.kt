package com.ankit.myjetpackcomposedemo.screen.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ankit.myjetpackcomposedemo.MainActivity
import com.ankit.myjetpackcomposedemo.ecommerse.model.ProductResponse
import com.ankit.myjetpackcomposedemo.ecommerse.result.NetworkResult
import com.ankit.myjetpackcomposedemo.ecommerse.viewmodel.EcommerceViewmodel

@Composable
fun CollectProductApiState(
    viewmodel: EcommerceViewmodel,
    onApiResultSuccess: @Composable (ArrayList<ProductResponse>) -> Unit,
    onApiResultLoading: @Composable () -> Unit,
    onApiResultFailed: (Throwable) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        viewmodel.productResponseState.collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)
            .also { resultState ->
                when (resultState.value) {
                    is NetworkResult.Error -> {
                        val error = (resultState.value as NetworkResult.Error).exception
                        Log.e(
                            "ApiError",
                            "${(resultState.value as NetworkResult.Error).exception}"
                        )
                        onApiResultFailed.invoke(error)
                    }

                    NetworkResult.Idle -> {
                        //                        splashScreen.setKeepOnScreenCondition { true }
                    }

                    NetworkResult.Loading -> {
                        onApiResultLoading.invoke()
//                        if (loading1) {
//                            ShowLoadingStateLoader()
//                        }
                    }

                    is NetworkResult.Success -> {
                        val data = (resultState.value as NetworkResult.Success<ArrayList<ProductResponse>>).data
                        onApiResultSuccess.invoke(data)
                        //  loading1 = !loading1
                        // ShowLoadingStateLoader()
//                        StartupApp(
//                            Modifier,
//                            (resultState.value as NetworkResult.Success<ArrayList<ProductResponse>>).data
//                        )
                    }
                }

            }
    }
}