package com.example.schedule.utils

import android.app.Application
import com.example.schedule.di.dataModule
import com.example.schedule.di.interactorModule
import com.example.schedule.di.repositoryModule
import com.example.schedule.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(dataModule, repositoryModule, interactorModule, viewModelModule)
        }
    }
}