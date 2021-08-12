package com.bcobsop.qvizapp

import android.app.Application
import com.amplitude.api.Amplitude

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        sInstance = this
        Amplitude.getInstance()
            .initialize(this, "6231950354fe85d672dd2f0b96412472")
            .enableForegroundTracking(this)
    }


    companion object {

        private lateinit var sInstance: App

        fun getInstance(): App {
            return sInstance
        }
    }
}