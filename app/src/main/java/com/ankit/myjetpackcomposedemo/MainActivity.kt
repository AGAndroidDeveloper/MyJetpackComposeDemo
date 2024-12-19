package com.ankit.myjetpackcomposedemo

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ankit.myjetpackcomposedemo.ecommerse.network.ApiProvider
import com.ankit.myjetpackcomposedemo.ecommerse.network.Repository
import com.ankit.myjetpackcomposedemo.ecommerse.viewmodel.EcommerceViewmodel
import com.ankit.myjetpackcomposedemo.screen.home.CollectProductApiState
import com.ankit.myjetpackcomposedemo.screen.home.HomeTopBar
import com.ankit.myjetpackcomposedemo.screen.home.PlacesListContent
import com.ankit.myjetpackcomposedemo.screen.home.ShowLoadingStateLoader
import com.ankit.myjetpackcomposedemo.ui.theme.MyJetpackComposeDemoTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var loading by remember { mutableStateOf(false) }
            val viewmodel: EcommerceViewmodel by viewModels()
            val repo = Repository(ApiProvider.getApiService()!!)
            val systemUiController = rememberSystemUiController()

            //  viewmodel.getProducts(repo)
            LaunchedEffect(Unit) {
                loading = !loading
                viewmodel.getProducts(repo)
            }

            MyJetpackComposeDemoTheme {
//                systemUiController.setStatusBarColor(
//                    color = MaterialTheme.colorScheme.primary, // Recommended primary color from the theme
//                    darkIcons = MaterialTheme.colorScheme.primary.luminance() > 0.5f // Light icons for dark backgrounds, dark icons for light backgrounds
//                )
                Surface(
                    tonalElevation = 5.dp,
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    StartupApp(viewmodel = viewmodel)
                    // CollectApiState(viewmodel, loading)
                }
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun StartupApp(modifier: Modifier = Modifier, viewmodel: EcommerceViewmodel) {
        var isVisible by remember {
            mutableStateOf(false)
        }
        val scrollBehavior =
            TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
        Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
            HomeTopBar(modifier, scrollBehavior)
        }, floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    isVisible = !isVisible
                },
//                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
//                contentColor = MaterialTheme.colorScheme.onTertiaryContainer
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(5.dp)
                ) {
                    Icon(Icons.Default.Edit, contentDescription = "icon")
                    AnimatedVisibility(
                        isVisible,
                    ) {
                        Text(
                            "Edit", modifier = modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }) { padding ->
            Spacer(modifier.padding(padding))
            CollectProductApiState(
                onApiResultLoading = {
                    ShowLoadingStateLoader()
                },
                onApiResultFailed = { th ->
                    Log.d("api failed", "failed :${th}")
                },
                onApiResultSuccess = { data ->
                    PlacesListContent(modifier, data = data) { productId ->
                        val idMap = data.map { it.id }
                        val isContaines = idMap.contains(productId)
                        Log.e(
                            "selectedProductId",
                            "isContaines = $isContaines:product :${data[productId].title} :id: $productId"
                        )
                    }

                },
                viewmodel = viewmodel
            )
        }
    }


    @Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
    @Composable
    fun GreetingPreviewNight() {
        MyJetpackComposeDemoTheme {
            Surface(
                tonalElevation = 5.dp,
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.fillMaxSize()
            ) {
//                val viewmodel: EcommerceViewmodel by viewModels()
//                StartupApp(viewmodel = viewmodel)
                // CollectApiState(viewmodel, loading)
            }
        }
    }

    @Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
    @Composable
    fun GreetingPreviewDay() {
        MyJetpackComposeDemoTheme {
            Surface(
                tonalElevation = 5.dp,
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.fillMaxSize()
            ) {
                val viewmodel: EcommerceViewmodel by viewModels()
             //   StartupApp(modifier = Modifier,ecommerceViewModel: EcommerceViewModel = viewModel())
                // CollectApiState(viewmodel, loading)
            }
        }
    }

}
