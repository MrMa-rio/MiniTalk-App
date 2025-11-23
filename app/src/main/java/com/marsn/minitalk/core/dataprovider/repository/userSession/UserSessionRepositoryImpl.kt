package com.marsn.minitalk.core.dataprovider.repository.userSession

import androidx.datastore.core.DataStore
import com.marsn.minitalk.core.domain.UserSession
import kotlinx.coroutines.flow.Flow


class UserSessionRepositoryImpl(
    private val dataStore: DataStore<UserSession?>
) : UserSessionRepository {

    override fun getUserSessionFlow(): Flow<UserSession?> =
        dataStore.data

    override suspend fun saveSession(session: UserSession) {
        dataStore.updateData {
            session
        }
    }

    override suspend fun clearSession() {
        dataStore.updateData {
            null
        }
    }
}
