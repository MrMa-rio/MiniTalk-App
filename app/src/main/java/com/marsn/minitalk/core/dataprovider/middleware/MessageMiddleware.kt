package com.marsn.minitalk.core.dataprovider.middleware

import com.marsn.minitalk.core.domain.proto.ChatMessage
import com.marsn.minitalk.core.usecase.conversation.ConversationUsecase
import com.marsn.minitalk.core.usecase.message.MessagesUseCase
import com.marsn.minitalk.core.usecase.users.UserSessionUsecase

interface MessageMiddleware {
    suspend fun onMessage(msg: ChatMessage)
}
