package com.marsn.minitalk

import android.app.Application
import com.marsn.minitalk.core.dataprovider.repository.ChatDatabaseProvider

class MiniTalkApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ChatDatabaseProvider.provider(this)
    }
}