package com.ankit.myjetpackcomposedemo

import android.app.Application

class EcommerceApplication : Application() {

    override fun onCreate() {
        super.onCreate()
//        startKoin {
//            // Log Koin into Android logger
//            androidLogger()
//            // Reference Android context
//            androidContext(this@EcommerceApplication)
//            // Load modules
//            modules(KoinModule.appModule)
//        }

    }


}