@file:Suppress("unused")

package com.hynes.james.fibtest

import android.app.Application
import com.hynes.james.fibtest.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {

        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(viewModelsModule))
        }
    }
}