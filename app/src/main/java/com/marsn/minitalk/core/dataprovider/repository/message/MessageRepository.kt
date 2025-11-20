package com.marsn.minitalk.core.dataprovider.repository.message

import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    suspend fun saveMessage(message: MessageEntity)
    suspend fun saveMessages(messages: List<MessageEntity>)
    suspend fun getLatestMessages(
        conversationId: Long,
        limit: Int = 50
    ): Flow<List<MessageEntity>>

    suspend fun getOlderMessages(
        conversationId: Long,
        limit: Int = 50,
        timestamp: Long,
    ): Flow<List<MessageEntity>>
}
