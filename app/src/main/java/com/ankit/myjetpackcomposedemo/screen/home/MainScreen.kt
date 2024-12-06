package com.ankit.myjetpackcomposedemo.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ankit.myjetpackcomposedemo.animation.color.ChangeBackgroundColorAnimateAsState


@Composable
 fun MainContent(
    color1: MutableState<Color>,
    backgroundColor: Color,
    modifier: Modifier
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { ->
        ChangeBackgroundColorAnimateAsState(
            modifier = modifier.padding(10.dp),
            color1,
            backgroundColor
        )
    }
}


