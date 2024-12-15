package com.ankit.myjetpackcomposedemo.screen.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ankit.myjetpackcomposedemo.ecommerse.model.ProductResponse


@Composable
fun PlacesListContent(modifier: Modifier, data: ArrayList<ProductResponse>) {
    LazyColumn(modifier = modifier) {

    }

}