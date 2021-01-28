package com.chiper.testchiper

import android.app.Application
import android.content.Context
import androidx.lifecycle.LifecycleObserver
import com.chiper.testchiper.di.networkModule
import com.chiper.testchiper.di.repositoryModule
import com.chiper.testchiper.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application(), LifecycleObserver {
    companion object {
        private lateinit var appContext: Context

        final fun getAppContext(): Context = appContext
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this

        startKoin {
            modules(
                arrayListOf(
                    networkModule,
                    viewModelModule,
                    repositoryModule
                )
            )
            androidContext(this@App)
        }
    }
}