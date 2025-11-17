package com.marsn.minitalk.core.dataprovider.repository.conversation

import com.marsn.minitalk.core.domain.Conversation
import kotlinx.coroutines.flow.Flow

interface ConversationRepository {

    suspend fun createConversation(conversation: ConversationEntity)

    suspend fun getConversationById(conversationId: String): Result<ConversationEntity>

    fun getAllConversations(): Flow<List<Conversation>>


    suspend fun getUserConversations(userId: Long): Result<ConversationEntity>

    suspend fun updateConversation(conversation: ConversationEntity): Result<Unit>


    suspend fun syncConversations(userId: Long): Result<Boolean>
}
