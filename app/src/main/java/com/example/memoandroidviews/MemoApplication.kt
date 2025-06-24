package com.example.memoandroidviews

import android.app.Application
import com.example.memoandroidviews.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MemoApplication)
            modules(appModule)
        }
    }
}