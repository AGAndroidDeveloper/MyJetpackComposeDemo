plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.google.firebase.crashlytics)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.ankit.myjetpackcomposedemo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.ankit.myjetpackcomposedemo"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // koin
//    implementation ("io.insert-koin:koin-androidx-compose:4.1.0-Beta1")
//    implementation("io.insert-koin:koin-bom:4.1.0-Beta1")
//    implementation("io.insert-koin:koin-core")
//    implementation("io.insert-koin:koin-androidx-compose-navigation:4.1.0-Beta1")
//    implementation ("org.koin:koin-androidx-viewmodel:3.2.0")

    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.34.0")

    implementation("io.coil-kt.coil3:coil-compose:3.0.4")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.0.4")

    val lifecycle_version = "2.8.7"
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
   // ksp("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")
    // retrofit
   // implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation(libs.squareup.retrofit2)
    implementation(libs.kotlinx.serialization.json)
    implementation (libs.gson)
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.11.0")
    //implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.crashlytics)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}