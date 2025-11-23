package com.marsn.minitalk

import android.app.Application
import com.marsn.minitalk.core.di.dataStoreModule
import com.marsn.minitalk.core.di.databaseModule
import com.marsn.minitalk.core.di.networkModule
import com.marsn.minitalk.core.di.repositoryModule
import com.marsn.minitalk.core.di.usecaseModule
import com.marsn.minitalk.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MiniTalkApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MiniTalkApp)
            modules(
                databaseModule,
                dataStoreModule,
                viewModelModule,
                repositoryModule,
                usecaseModule,
                networkModule
            )
        }
    }
}