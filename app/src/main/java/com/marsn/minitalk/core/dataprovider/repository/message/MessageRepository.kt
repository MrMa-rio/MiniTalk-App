package com.marsn.minitalk.core.dataprovider.repository.message

import kotlinx.coroutines.flow.Flow

interface MessageRepository {

    fun getMessages(conversationId: String): Flow<List<MessageEntity>>

    suspend fun loadOlderMessages(
        conversationId: String,
        beforeTimestamp: Long,
        limit: Int
    ): Flow<List<MessageEntity>>

    suspend fun getLastMessage(conversationId: String): MessageEntity?

    suspend fun sendMessage(message: MessageEntity)

    suspend fun saveIncomingMessage(message: MessageEntity)

    suspend fun updateIsSent(messageId: Long, value: Boolean)
    suspend fun updateIsDelivered(messageId: Long, value: Boolean)
    suspend fun updateIsRead(messageId: Long, value: Boolean)
    suspend fun updateIsDeleted(messageId: Long, value: Boolean)
    suspend fun updateIsEdited(messageId: Long, value: Boolean)

    suspend fun deleteConversationMessages(conversationId: String)
}
