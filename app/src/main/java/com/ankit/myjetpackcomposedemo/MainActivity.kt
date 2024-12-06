package com.ankit.myjetpackcomposedemo

import MyJetpackComposeDemoTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ankit.myjetpackcomposedemo.screen.home.HomeTopBar
import com.ankit.myjetpackcomposedemo.screen.home.MainContent
import com.ankit.myjetpackcomposedemo.ui.theme.dynamicBackgroundColor


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        setContent {
            StartupApp(Modifier)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun StartupApp(modifier: Modifier) {
        val backgroundColor = dynamicBackgroundColor
        val colorState  = remember {
            mutableStateOf(backgroundColor)
        }

        val targetColorState by
        animateColorAsState(targetValue = colorState.value, label = "color Animation")

        var isVisible by remember {
            mutableStateOf(false)
        }

        MyJetpackComposeDemoTheme {
            val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                containerColor = targetColorState,
                topBar = {
                    HomeTopBar(modifier,scrollBehavior)
                },
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
                                    modifier = modifier.padding(start = 8.dp)
                                )
                            }
                        }
                    }
                }
            ) { padding ->
                MainContent(colorState, backgroundColor, modifier = modifier.padding(padding))
            }
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