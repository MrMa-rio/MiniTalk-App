package com.marsn.minitalk.core.usecase.conversation

import com.marsn.minitalk.core.domain.conversation.Conversation
import com.marsn.minitalk.core.domain.contact.Contact
import com.marsn.minitalk.core.domain.conversation.ConversationItem
import kotlinx.coroutines.flow.Flow

interface ConversationUsecase {
    suspend fun createConversation(senderId: Long, destinyId: Long)

    suspend fun consultConversation(conversationId: String): Conversation?

    suspend fun consultAllConversations(currentUserId: Long): Flow<List<ConversationItem>>

    suspend fun consultUserConversation(userId: Long): Conversation

    suspend fun updateConversation(conversation: Conversation)


}