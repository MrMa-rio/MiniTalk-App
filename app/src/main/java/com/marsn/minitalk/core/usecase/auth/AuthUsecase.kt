package com.marsn.minitalk.core.usecase.auth

import com.marsn.minitalk.core.domain.Token
import com.marsn.minitalk.core.domain.UserProfile

interface AuthUsecase {
    suspend fun authLogin(username: String, password: String): Token

    suspend fun getUserProfile(): UserProfile?


}