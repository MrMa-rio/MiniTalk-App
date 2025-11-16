package com.marsn.minitalk.core.usecase.conversation

import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationEntity
import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationRepository
import com.marsn.minitalk.core.domain.Conversation
import kotlinx.coroutines.flow.Flow

class ConversationUsecaseImpl(
    private val repository: ConversationRepository
) : ConversationUsecase {
    override suspend fun createConversation(conversation: Conversation) {

        repository.createConversation(conversation.toEntity())

    }

    override suspend fun consultConversation(conversationId: Long): Conversation? {
        TODO("Not yet implemented")
    }

    override  fun consultAllConversations(): Flow<List<Conversation>> {

        return repository.getAllConversations()

    }

    override suspend fun consultUserConversation(userId: Long): Conversation {
        TODO("Not yet implemented")
    }

    override suspend fun updateConversation(conversation: Conversation) {

        repository.updateConversation(conversation.toEntity())

    }
}