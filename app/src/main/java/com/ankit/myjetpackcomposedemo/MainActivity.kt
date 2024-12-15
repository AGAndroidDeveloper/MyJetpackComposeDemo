package com.ankit.myjetpackcomposedemo

import MyJetpackComposeDemoTheme
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ankit.myjetpackcomposedemo.ecommerse.network.ApiProvider
import com.ankit.myjetpackcomposedemo.ecommerse.network.Repository
import com.ankit.myjetpackcomposedemo.ecommerse.result.NetworkResult
import com.ankit.myjetpackcomposedemo.ecommerse.viewmodel.EcommerceViewmodel
import com.ankit.myjetpackcomposedemo.screen.home.HomeTopBar
import com.ankit.myjetpackcomposedemo.screen.home.MainContent
import com.ankit.myjetpackcomposedemo.screen.home.PlacesListContent
import com.ankit.myjetpackcomposedemo.ui.theme.dynamicBackgroundColor
import com.ankit.myjetpackcomposedemo.ui.theme.primaryDark
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            val viewmodel: EcommerceViewmodel by viewModels()
            val repo = Repository(ApiProvider.getApiService()!!)
            viewmodel.getProducts(repo)
            StartupApp(Modifier, viewmodel)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun StartupApp(modifier: Modifier, viewmodel: EcommerceViewmodel) {
        val backgroundColor = dynamicBackgroundColor
        val colorState = remember {
            mutableStateOf(backgroundColor)
        }

        val targetColorState by
        animateColorAsState(targetValue = colorState.value, label = "color Animation")

        var isVisible by remember {
            mutableStateOf(false)
        }

//        val viewmodel: EcommerceViewmodel by viewModels()
//        viewmodel.getProducts(repo)

        MyJetpackComposeDemoTheme {
            val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                containerColor = targetColorState,
                topBar = {
                    HomeTopBar(modifier, scrollBehavior)
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
                Spacer(Modifier.padding(padding))
                val state = viewmodel.productResponseState.collectAsStateWithLifecycle()
                val progressState = remember { mutableStateOf(false) }
                when (state.value) {
                    is NetworkResult.Error -> {
                        progressState.value = false
                        Log.e("ApiError", "${(state.value as NetworkResult.Error).exception}")
                    }

                    NetworkResult.Idle -> {
                        // do nothing here
                    }

                    NetworkResult.Loading -> {
                        progressState.value = true
                        if (progressState.value){
                            Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()) {
                                Column {
                                    CircularProgressIndicator(
                                        progress = {
                                            .4F
                                        },
                                        strokeWidth = 1.dp,
                                        trackColor = ProgressIndicatorDefaults.circularIndeterminateTrackColor,
                                        strokeCap = StrokeCap.Round,
                                    )
                                    Spacer(modifier = modifier.height(5.dp))
                                    Text(
                                        "Please wait...",
                                        style = TextStyle(
                                            fontStyle = FontStyle.Normal,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }

                            }
                        }
                    }

                    is NetworkResult.Success -> {
//                        progressState.value = false
//                        val data = state.value
//                        println(data)

                        //  PlacesListContent(modifier,data = data)

                    }
                }


//
//                MainContent(
//                    colorState,
//                    backgroundColor,
//                    modifier = modifier.padding(padding)
//                )
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