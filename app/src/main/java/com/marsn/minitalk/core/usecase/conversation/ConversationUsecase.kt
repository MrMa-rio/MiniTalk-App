package com.marsn.minitalk.core.usecase.conversation

import com.marsn.minitalk.core.domain.Conversation
import com.marsn.minitalk.core.domain.contact.Contact
import kotlinx.coroutines.flow.Flow

interface ConversationUsecase {
    suspend fun createConversation(conversation: Conversation)

    suspend fun consultConversation(conversationId: String): Conversation?

    fun consultAllConversations(): Flow<List<Conversation>>

    suspend fun consultUserConversation(userId: Long): Conversation

    suspend fun updateConversation(conversation: Conversation)


}