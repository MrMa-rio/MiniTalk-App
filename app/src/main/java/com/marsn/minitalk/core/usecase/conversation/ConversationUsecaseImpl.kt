package com.marsn.minitalk.core.usecase.conversation

import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationRepository
import com.marsn.minitalk.core.domain.conversation.Conversation
import com.marsn.minitalk.core.domain.conversation.ConversationItem
import kotlinx.coroutines.flow.Flow

class ConversationUsecaseImpl(
    private val repository: ConversationRepository
) : ConversationUsecase {
    override suspend fun createConversation(senderId: Long, destinyId: Long ) {
        repository.createPrivateConversation(senderId, destinyId )
    }

    override suspend fun consultConversation(conversationId: String): Conversation? {
        TODO("Not yet implemented")
    }

    override suspend fun consultAllConversations(currentUserId: Long): Flow<List<ConversationItem>> {

        return repository.getConversationsPreview(currentUserId)

    }

    override suspend fun consultUserConversation(userId: Long): Conversation {
        TODO("Not yet implemented")
    }

    override suspend fun updateConversation(conversation: Conversation) {

        TODO("Not yet implemented")

    }
}