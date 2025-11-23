package com.marsn.minitalk.core.usecase.users

import com.marsn.minitalk.core.dataprovider.repository.userSession.UserSessionRepository
import com.marsn.minitalk.core.domain.UserSession
import kotlinx.coroutines.flow.first

class UserSessionUsecaseImpl(
    private val userRepository: UserSessionRepository
) : UserSessionUsecase {
    override suspend fun consultUserSession(): UserSession? {
        return userRepository.getUserSessionFlow().first()
    }
}