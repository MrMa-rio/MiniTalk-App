package com.marsn.minitalk.core.dataprovider.repository.message.messageChunk

import com.marsn.minitalk.core.dataprovider.repository.message.MessageEntity

interface MessageChunkRepository {

    suspend fun saveChunk(chunk: MessageChunkEntity): Result<Unit>


    suspend fun saveOrUpdateChunk(chunk: MessageChunkEntity)
    suspend fun getChunks(conversationId: Long, limit: Int): List<MessageChunkEntity>

    suspend fun getInitialChunks(
        conversationId: Long,
        limit: Int = 5
    ): Result<List<MessageChunkEntity>>

    suspend fun getNextChunks(
        conversationId: Long,
        cursor: Long,
        limit: Int = 5
    ): Result<List<MessageChunkEntity>>

    suspend fun deleteChunksFromConversation(conversationId: Long): Result<Unit>

    suspend fun createChunkFromMessages(
        conversationId: Long,
        messages: List<MessageEntity>
    ): Result<MessageChunkEntity>
}
