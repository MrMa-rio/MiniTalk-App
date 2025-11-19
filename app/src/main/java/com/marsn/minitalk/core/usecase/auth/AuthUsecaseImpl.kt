package com.marsn.minitalk.core.usecase.auth

import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationRepository
import com.marsn.minitalk.core.domain.Conversation
import com.marsn.minitalk.core.usecase.conversation.ConversationUsecase
import kotlinx.coroutines.flow.Flow

class AuthUsecaseImpl(
    private val repository: ConversationRepository
) : ConversationUsecase {
    override suspend fun createConversation(conversation: Conversation) {

        repository.createConversation(conversation.toEntity())

    }

    override suspend fun consultConversation(conversationId: String): Conversation? {
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