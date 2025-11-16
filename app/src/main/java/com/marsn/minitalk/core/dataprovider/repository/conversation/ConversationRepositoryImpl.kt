package com.marsn.minitalk.core.dataprovider.repository.conversation

import com.marsn.minitalk.core.domain.Conversation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class ConversationRepositoryImpl(
    private val conversationDao: ConversationDao
) : ConversationRepository {
    override suspend fun createConversation(conversation: ConversationEntity) {

         conversationDao.insertConversation(conversation)

    }

    override suspend fun getConversationById(conversationId: Long): Result<ConversationEntity> {
        TODO("Not yet implemented")
    }


    override fun getAllConversations(): Flow<List<Conversation>> {
        return conversationDao
            .getAllConversations()
            .map { entities ->
                entities.map { it.toModel() }
            }
            .catch { e ->
                e.printStackTrace()
                emit(emptyList())
            }
    }



    override suspend fun getUserConversations(userId: Long): Result<ConversationEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun updateConversation(conversation: ConversationEntity): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun syncConversations(userId: Long): Result<Boolean> {
        TODO("Not yet implemented")
    }
}