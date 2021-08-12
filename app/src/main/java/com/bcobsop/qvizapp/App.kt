package com.bcobsop.qvizapp

import android.app.Application
import com.amplitude.api.Amplitude

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        sInstance = this
        Amplitude.getInstance()
            .initialize(this, "3031a61ead2f7482d87c899794cec751")
            .enableForegroundTracking(this)
    }


    companion object {

        private lateinit var sInstance: App

        fun getInstance(): App {
            return sInstance
        }
    }
}