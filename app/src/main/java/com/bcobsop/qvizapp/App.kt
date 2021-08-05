package com.bcobsop.qvizapp

import android.app.Application

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        sInstance = this
    }


    companion object {

        private lateinit var sInstance: App

        fun getInstance(): App {
            return sInstance
        }
    }
}