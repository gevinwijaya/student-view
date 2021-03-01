package com.mobile.programming.studentview.injection

import android.app.Application
import android.content.Context

class MyApp(): Application() {

    companion object{
        lateinit var instance: MyApp

        fun getApplication(): MyApp {
            return instance
        }
        fun getContext(): Context{
            return instance
        }
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }

}