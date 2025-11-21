package com.marsn.minitalk.core.usecase.users

import com.marsn.minitalk.core.dataprovider.repository.userSession.UserSessionRepository
import com.marsn.minitalk.core.domain.UserSession
import com.marsn.minitalk.ui.feature.splashCustomized.SplashEvent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn

class UserSessionUsecaseImpl(
    private val userRepository: UserSessionRepository
) : UserSessionUsecase {
    override suspend fun consultUserSession(): UserSession? {
        return userRepository.getUserSessionFlow().first()
    }
}