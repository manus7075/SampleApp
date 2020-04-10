package com.example.newtestapplication

import android.app.Application
import com.example.newtestapplication.data.di.networkModule
import com.example.newtestapplication.data.di.repositorySourceModule
import org.koin.core.context.startKoin

class NewApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(arrayListOf(networkModule, repositorySourceModule))
        }
    }
}