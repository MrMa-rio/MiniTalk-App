package com.marsn.minitalk.dataprovider.repository.message.messageChunk

import com.marsn.minitalk.dataprovider.repository.message.MessageEntity
import kotlinx.serialization.json.Json
import java.util.UUID

class MessageChunkRepositoryImpl(
    private val chunkDao: MessageChunkDao,
    private val json: Json
) : MessageChunkRepository {

    override suspend fun saveChunk(chunk: MessageChunkEntity): Result<Unit> = runCatching {
        chunkDao.insertChunk(chunk)
    }

    override suspend fun getInitialChunks(
        conversationId: String,
        limit: Int
    ): Result<List<MessageChunkEntity>> = runCatching {
        chunkDao.getChunks(conversationId, limit)
    }

    override suspend fun getNextChunks(
        conversationId: String,
        cursor: Long,
        limit: Int
    ): Result<List<MessageChunkEntity>> = runCatching {
        chunkDao.getChunksFromCursor(conversationId, cursor, limit)
    }

    override suspend fun deleteChunksFromConversation(conversationId: String): Result<Unit> = runCatching {
        chunkDao.deleteConversationChunks(conversationId)
    }

    override suspend fun createChunkFromMessages(
        conversationId: String,
        messages: List<MessageEntity>
    ): Result<MessageChunkEntity> = runCatching {

        if (messages.isEmpty()) throw IllegalStateException("Chunk must have at least 1 message")

        val startTs = messages.last().createdAt
        val endTs = messages.first().createdAt

        val jsonString = json.encodeToString(messages)

        MessageChunkEntity(
            chunkId = UUID.randomUUID().toString(),
            conversationId = conversationId,
            startTimestamp = startTs,
            endTimestamp = endTs,
            messagesJson = jsonString
        )
    }
}
