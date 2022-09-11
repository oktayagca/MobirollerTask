package com.example.caseshopiroller

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CaseShopirollerApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun onTerminate() {
        super.onTerminate()
    }

}