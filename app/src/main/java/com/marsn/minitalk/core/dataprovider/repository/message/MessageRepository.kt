package com.marsn.minitalk.core.dataprovider.repository.message

interface MessageRepository {
    suspend fun saveMessage(message: MessageEntity)
    suspend fun saveMessages(messages: List<MessageEntity>)
    suspend fun getMessages(
        conversationId: String,
        limit: Int,
        offset: Int
    ): List<MessageEntity>

    suspend fun countMessages(conversationId: String): Int
}
