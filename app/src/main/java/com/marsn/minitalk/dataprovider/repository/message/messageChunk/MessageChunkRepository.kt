package com.marsn.minitalk.dataprovider.repository.message.messageChunk

import com.marsn.minitalk.dataprovider.repository.message.MessageEntity

interface MessageChunkRepository {

    suspend fun saveChunk(chunk: MessageChunkEntity): Result<Unit>

    suspend fun getInitialChunks(
        conversationId: String,
        limit: Int = 5
    ): Result<List<MessageChunkEntity>>

    suspend fun getNextChunks(
        conversationId: String,
        cursor: Long,
        limit: Int = 5
    ): Result<List<MessageChunkEntity>>

    suspend fun deleteChunksFromConversation(conversationId: String): Result<Unit>

    suspend fun createChunkFromMessages(
        conversationId: String,
        messages: List<MessageEntity>
    ): Result<MessageChunkEntity>
}
