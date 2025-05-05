package com.app.testpexel

import android.app.Application
import com.app.testpexel.di.apiModule
import com.app.testpexel.di.networkModule
import com.app.testpexel.di.repositoryModule
import com.app.testpexel.di.viewModelModule
import com.app.testpexel.utils.GlideEngine
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PexelApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        GlideEngine.instance
        startKoin {
            androidContext(applicationContext)
            modules(
                listOf(
                    networkModule,
                    apiModule,
                    repositoryModule,
                    viewModelModule,
                )
            )
        }
    }
}