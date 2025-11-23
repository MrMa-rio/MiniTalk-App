package com.marsn.minitalk.core.usecase.conversation

import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationRepository
import com.marsn.minitalk.core.domain.UserProfile
import com.marsn.minitalk.core.domain.contact.Contact
import com.marsn.minitalk.core.domain.conversation.Conversation
import com.marsn.minitalk.core.domain.conversation.ConversationItem
import com.marsn.minitalk.core.usecase.users.ContactUsecase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ConversationUsecaseImpl(
    private val repository: ConversationRepository,
    private val contactUsecase: ContactUsecase
) : ConversationUsecase {
    override suspend fun createConversation(senderId: Long, destinyId: Long) {
        repository.createPrivateConversation(senderId, destinyId)
    }

    override suspend fun consultConversation(conversationId: String): Conversation? {
        val entity = repository.getConversationByConversationId(conversationId)
        return entity?.toModel()
    }

    override suspend fun consultParticipantsByConversationId(conversationId: String): List<Contact> {
        val participantsIds = repository.getParticipantsByConversationId(conversationId)
            .first()

        return participantsIds.mapNotNull { id ->
            contactUsecase.consultContact(id.participantId)
        }
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
        return conversation ?: repository.createPrivateConversation(currentUserId, userId)
            .toModel()
    }

    override suspend fun updateConversation(conversation: Conversation) {

        TODO("Not yet implemented")

    }
}