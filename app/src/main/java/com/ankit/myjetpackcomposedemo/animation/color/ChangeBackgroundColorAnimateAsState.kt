package com.ankit.myjetpackcomposedemo.animation.color

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ankit.myjetpackcomposedemo.ColorWrapper
import com.ankit.myjetpackcomposedemo.Constant.colorWrapperList

@Composable
fun ChangeBackgroundColorAnimateAsState(
    modifier: Modifier,
    colorState: MutableState<Color>,
    backgroundColor: Color
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier.fillMaxWidth()) {
            colorWrapperList.forEachIndexed { index, colorWrapper ->
                DrawButton(colorState, colorWrapper, index)
            }
        }
        DrawButton(colorState, ColorWrapper(name = "reset", color = backgroundColor), -1)
    }

}

@Composable
private fun DrawButton(
    colorState: MutableState<Color>,
    colorWrapper: ColorWrapper,
    index: Int
) {
    ElevatedButton(
        onClick = {
            colorState.value = colorWrapper.color
            Log.e("onItemClick", index.toString())
        },
        shape = RoundedCornerShape(6.dp),
        elevation =
        ButtonDefaults.buttonElevation(
            defaultElevation = 8.dp,
            pressedElevation = 12.dp
        )
    ) {
        Text(text = colorWrapper.name ?: "", color = colorWrapper.color.copy(.8f))
    }
}
