package com.marsn.minitalk.core.dataprovider.repository.conversation

import com.marsn.minitalk.core.shared.enums.TypeParticipant
import kotlinx.coroutines.flow.Flow

interface ConversationRepository {

    suspend fun getConversationByConversationId(conversationId: Long): ConversationEntity?

    suspend fun getConversationsForParticipantId(participantId: Long): Flow<List<ConversationEntity>>

    suspend fun getParticipantsByConversationId(conversationId: Long): Flow<List<ConversationParticipantsEntity>>

    suspend fun createPrivateConversation(senderId: Long, destinyId: Long): ConversationEntity


    suspend fun createGroupConversation(creatorId: Long, groupName: String, participants: List<Long>): ConversationEntity

    suspend fun deleteConversation(conversation: ConversationEntity)

    suspend fun addParticipant(conversationId: Long, participantId: Long, role: TypeParticipant = TypeParticipant.MEMBER)

    suspend fun removeParticipant(conversationId: Long, participantId: Long)
}

