package com.marsn.minitalk.core.usecase.message

import com.marsn.minitalk.core.domain.MessageText
import com.marsn.minitalk.core.domain.proto.ChatMessage
import kotlinx.coroutines.flow.Flow

interface MessagesUseCase {

    suspend fun sendMessage(message: ChatMessage)

    suspend fun consultMessages(conversationId: String): Flow<List<MessageText>>

    suspend fun consultOlderMessages(
        conversationId: String,
        timestamp: Long,
        limit: Int = 50
    ): Flow<List<MessageText>>

    suspend fun saveMessage(message: ChatMessage)

}
