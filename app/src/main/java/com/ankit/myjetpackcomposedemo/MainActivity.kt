package com.ankit.myjetpackcomposedemo

import MyJetpackComposeDemoTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ankit.myjetpackcomposedemo.animation.color.ChangeBackgroundColorAnimateAsState
import com.ankit.myjetpackcomposedemo.ui.theme.dynamicBackgroundColor


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            val backgroundColor = dynamicBackgroundColor
            val color1 = remember {
                mutableStateOf(backgroundColor)
            }

            val colorState by
            animateColorAsState(targetValue = color1.value, label = "color Animation")

            var isVisible by remember {
                mutableStateOf(false)
            }

            MyJetpackComposeDemoTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = colorState,
                    topBar ={

                    } ,
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                isVisible = !isVisible
                            }
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.padding(5.dp)
                            ) {
                                Icon(Icons.Default.Edit, contentDescription = "icon")
                                AnimatedVisibility(
                                    isVisible,
//                                    exit = slideOutHorizontally(
//                                        animationSpec = spring(
//                                            dampingRatio = Spring.DampingRatioMediumBouncy,
//                                            stiffness = Spring.StiffnessMedium
//                                        )
//                                    ),
//                                    enter = slideInHorizontally(
//                                        animationSpec = spring(
//                                            dampingRatio = Spring.DampingRatioMediumBouncy,
//                                            stiffness = Spring.StiffnessMedium
//                                        )
//                                    )
                                ) {
                                    Text(
                                        "Edit",
                                        modifier = Modifier.padding(start = 8.dp)
                                    )
                                }
                            }
                        }
                    }
                ) { padding ->
                    MainContent(color1, backgroundColor, modifier = Modifier.padding(padding))
                }
            }
        }
    }

    @Composable
    private fun MainContent(
        color1: MutableState<Color>,
        backgroundColor: Color,
        modifier: Modifier
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { ->
            ChangeBackgroundColorAnimateAsState(
                modifier = Modifier.padding(10.dp),
                color1,
                backgroundColor
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyJetpackComposeDemoTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { ->
//            Greeting(
//                name = "Android",
//                modifier = Modifier
//            )
        }
    }
}