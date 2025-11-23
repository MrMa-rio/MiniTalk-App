package com.marsn.minitalk.core.dataprovider.repository.conversation

import com.marsn.minitalk.core.domain.conversation.Conversation
import com.marsn.minitalk.core.domain.conversation.ConversationItem
import com.marsn.minitalk.core.shared.enums.TypeParticipant
import kotlinx.coroutines.flow.Flow

interface ConversationRepository {

    suspend fun getConversationsPreview(currentUserId: Long): Flow<List<ConversationItem>>

    suspend fun getConversationByConversationId(conversationId: String): ConversationEntity?

    suspend fun getConversationsForParticipantId(participantId: Long): Flow<List<ConversationEntity>>

    suspend fun getParticipantsByConversationId(conversationId: String): Flow<List<ConversationParticipantsEntity>>

    suspend fun createPrivateConversation(senderId: Long, destinyId: Long): ConversationEntity


    suspend fun createGroupConversation(creatorId: Long, groupName: String, participants: List<Long>): ConversationEntity

    suspend fun deleteConversation(conversation: ConversationEntity)

    suspend fun addParticipant(conversationId: String, participantId: Long, role: TypeParticipant = TypeParticipant.MEMBER)

    suspend fun removeParticipant(conversationId: String, participantId: Long)
    suspend fun getConversationByParticipantId(participantId: Long): Conversation?
    suspend fun getConversationsGroupForParticipantId(participantId: Long): Flow<List<ConversationEntity>>
}

