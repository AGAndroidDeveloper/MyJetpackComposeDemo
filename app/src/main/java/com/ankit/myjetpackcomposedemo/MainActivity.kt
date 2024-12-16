package com.ankit.myjetpackcomposedemo

import com.ankit.myjetpackcomposedemo.ui.theme.MyJetpackComposeDemoTheme
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ankit.myjetpackcomposedemo.ecommerse.model.ProductResponse
import com.ankit.myjetpackcomposedemo.ecommerse.network.ApiProvider
import com.ankit.myjetpackcomposedemo.ecommerse.network.Repository
import com.ankit.myjetpackcomposedemo.ecommerse.result.NetworkResult
import com.ankit.myjetpackcomposedemo.ecommerse.viewmodel.EcommerceViewmodel
import com.ankit.myjetpackcomposedemo.screen.home.HomeTopBar
import com.ankit.myjetpackcomposedemo.screen.home.PlacesListContent
import com.ankit.myjetpackcomposedemo.ui.theme.dynamicBackgroundColor
import com.ankit.myjetpackcomposedemo.ui.theme.primaryLight


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            var loading by remember { mutableStateOf(false) }
            val viewmodel: EcommerceViewmodel by viewModels()
            val repo = Repository(ApiProvider.getApiService()!!)
            //  viewmodel.getProducts(repo)
            LaunchedEffect(Unit) {
                loading = !loading
                viewmodel.getProducts(repo)
            }

            MyJetpackComposeDemoTheme{
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    viewmodel.productResponseState.collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)
                        .also { resultState ->
                            when (resultState.value) {
                                is NetworkResult.Error -> {
                                    loading = !loading
                                    Log.e(
                                        "ApiError",
                                        "${(resultState.value as NetworkResult.Error).exception}"
                                    )
                                }

                                NetworkResult.Idle -> {
//                        splashScreen.setKeepOnScreenCondition { true }
                                }

                                NetworkResult.Loading -> {
                                    if (loading) {
                                        //  ShowLoadingStateLoader()
                                    }
                                }

                                is NetworkResult.Success -> {
                                    loading = !loading
                                    ShowLoadingStateLoader()
//                                StartupApp(
//                                    Modifier,
//                                    (resultState.value as NetworkResult.Success<ArrayList<ProductResponse>>).data
//                                )
                                }
                            }

                        }
                }
            }


        }
    }

    @Composable
    private fun ShowLoadingStateLoader() {
        Column(
            modifier = Modifier.wrapContentSize()
                .clip(RoundedCornerShape(20.dp))
                .background(color = primaryLight)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(45.dp)
                    .padding(bottom = 10.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
            Text(
                "Loading...",
                modifier = Modifier.padding(10.dp),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun StartupApp(modifier: Modifier, data: ArrayList<ProductResponse>) {
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

        val scrollBehavior =
            TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
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
            Spacer(modifier.padding(padding))
            PlacesListContent(modifier, data = data) { productId ->
                val idMap = data.map { it.id }
                val isContaines = idMap.contains(productId)
                Log.e(
                    "selectedProductId",
                    "isContaines = $isContaines:product :${data[productId].title} :id: $productId"
                )
            }
//                MainContent(
//                    colorState,
//                    backgroundColor,
//                    modifier = modifier.padding(padding)
//                )
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