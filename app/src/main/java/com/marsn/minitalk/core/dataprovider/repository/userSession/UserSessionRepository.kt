package com.marsn.minitalk.core.dataprovider.repository.userSession

import com.marsn.minitalk.core.domain.UserSession
import kotlinx.coroutines.flow.Flow


interface UserSessionRepository {

    fun getUserSessionFlow(): Flow<UserSession?>

    suspend fun saveSession(session: UserSession)

    suspend fun clearSession()
}
