package com.marsn.minitalk.core.dataprovider.repository.message

import kotlinx.coroutines.flow.Flow

interface MessageRepository {

    fun getMessages(conversationId: Long): Flow<List<MessageEntity>>

    suspend fun loadOlderMessages(
        conversationId: Long,
        beforeTimestamp: Long,
        limit: Int
    ): Flow<List<MessageEntity>>

    suspend fun getLastMessage(conversationId: Long): MessageEntity?

    suspend fun sendMessage(message: MessageEntity)

    suspend fun saveIncomingMessage(message: MessageEntity)

    suspend fun updateIsSent(messageId: Long, value: Boolean)
    suspend fun updateIsDelivered(messageId: Long, value: Boolean)
    suspend fun updateIsRead(messageId: Long, value: Boolean)
    suspend fun updateIsDeleted(messageId: Long, value: Boolean)
    suspend fun updateIsEdited(messageId: Long, value: Boolean)

    suspend fun deleteConversationMessages(conversationId: Long)
}
