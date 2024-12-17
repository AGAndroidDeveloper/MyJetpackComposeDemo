package com.ankit.myjetpackcomposedemo.screen.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ankit.myjetpackcomposedemo.ui.theme.MyJetpackComposeDemoTheme

@Composable
 fun ShowLoadingStateLoader() {
    Column(
        modifier = Modifier
            .size(150.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(45.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            "Loading...",
            modifier = Modifier.padding(10.dp),
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreviewNight() {
    MyJetpackComposeDemoTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { ->
//            Greeting(
//                name = "Android",
//                modifier = Modifier
//            )
            ShowLoadingStateLoader()

        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun GreetingPreviewDay() {
    MyJetpackComposeDemoTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { ->
//            Greeting(
//                name = "Android",
//                modifier = Modifier
//            )
            ShowLoadingStateLoader()

    }
}

}
