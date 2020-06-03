package com.example.workto_android

import android.app.Application
import com.example.workto_android.di.moduleList
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(moduleList)
        }
    }
}