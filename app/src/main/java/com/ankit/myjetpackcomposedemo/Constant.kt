package com.ankit.myjetpackcomposedemo

import androidx.compose.ui.graphics.Color

enum class ColorsEnum {
    RED, GREEN, BLUE, YELLOW
}

object Constant {
    val colorWrapperList = listOf(
        ColorWrapper(
            name = ColorsEnum.GREEN.name,
            color = Color.Green
        ),
        ColorWrapper(
            name = ColorsEnum.RED.name,
            color = Color.Red
        ),
        ColorWrapper(
            name = ColorsEnum.BLUE.name,
            color = Color.Blue
        ),
//        ColorWrapper(
//            name = ColorsEnum.YELLOW.name,
//            color = Color.Yellow
//        ),

        )
}

val places = arrayListOf(
    "Paris",
    "New York",
    "Tokyo",
    "Sydney",
    "London",
    "Dubai",
    "Barcelona",
    "Rome",
    "Bangkok",
    "Istanbul",
    "Moscow",
    "Berlin",
    "Mumbai",
    "Beijing",
    "Cape Town",
    "Rio de Janeiro",
    "Singapore",
    "Los Angeles",
    "Amsterdam",
    "Toronto"
)