package com.marsn.minitalk.core.dataprovider.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import com.marsn.minitalk.core.domain.UserSession
import com.marsn.minitalk.infraestructure.UserSessionSerializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

fun createUserSessionDataStore(context: Context): DataStore<UserSession?> =
    DataStoreFactory.create(
        serializer = UserSessionSerializer,
        scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
        produceFile = {
            context.filesDir
                .resolve("datastore")
                .apply { mkdirs() }
                .resolve("user_session.json")
        }
    )
