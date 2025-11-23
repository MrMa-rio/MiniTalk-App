package com.marsn.minitalk.core.usecase.auth

import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationRepository
import com.marsn.minitalk.core.domain.conversation.Conversation
import com.marsn.minitalk.core.domain.Token
import com.marsn.minitalk.core.domain.UserProfile
import com.marsn.minitalk.core.usecase.conversation.ConversationUsecase
import kotlinx.coroutines.flow.Flow

class AuthUsecaseImpl(
    private val repository: ConversationRepository
) : AuthUsecase {
    override suspend fun authLogin(username: String, password: String): Token {

       TODO("Not yet implemented")

    }

    override suspend fun getUserProfile(): UserProfile? {
        TODO("Not yet implemented")
    }


}