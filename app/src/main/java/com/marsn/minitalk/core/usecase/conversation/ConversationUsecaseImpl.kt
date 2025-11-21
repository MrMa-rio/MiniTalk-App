package com.marsn.minitalk.core.usecase.conversation

import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationRepository
import com.marsn.minitalk.core.domain.conversation.Conversation
import com.marsn.minitalk.core.domain.conversation.ConversationItem
import kotlinx.coroutines.flow.Flow

class ConversationUsecaseImpl(
    private val repository: ConversationRepository
) : ConversationUsecase {
    override suspend fun createConversation(senderId: Long, destinyId: Long) {
        repository.createPrivateConversation(senderId, destinyId)
    }

    override suspend fun consultConversation(conversationId: String): Conversation? {
        val entity = repository.getConversationByConversationId(conversationId)
        return entity?.toModel()
    }

    override suspend fun consultAllConversations(currentUserId: Long): Flow<List<ConversationItem>> {

        return repository.getConversationsPreview(currentUserId)

    }

    override suspend fun consultUserConversation(userId: Long): Conversation? {
        return repository.getConversationByParticipantId(userId)
    }

    override suspend fun consultOrCreateUserConversation(
        currentUserId: Long,
        userId: Long
    ): Conversation {
        val conversation = repository.getConversationByParticipantId(userId)
        return conversation ?: repository.createPrivateConversation(currentUserId, userId).toModel()
    }

    override suspend fun updateConversation(conversation: Conversation) {

        TODO("Not yet implemented")

    }
}