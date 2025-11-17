package com.marsn.minitalk.core.dataprovider.repository.message.messageChunk

import com.marsn.minitalk.core.dataprovider.repository.message.MessageEntity
import kotlinx.serialization.json.Json
import java.util.UUID

class MessageChunkRepositoryImpl(
    private val dao: MessageChunkDao,
    private val json: Json
) : MessageChunkRepository {

    override suspend fun saveChunk(chunk: MessageChunkEntity): Result<Unit> = runCatching {
        dao.insertChunk(chunk)
    }

    override suspend fun getInitialChunks(
        conversationId: String,
        limit: Int
    ): Result<List<MessageChunkEntity>> = runCatching {
        dao.getChunks(conversationId, limit)
    }

    override suspend fun getNextChunks(
        conversationId: String,
        cursor: Long,
        limit: Int
    ): Result<List<MessageChunkEntity>> = runCatching {
        dao.getChunksFromCursor(conversationId, cursor, limit)
    }

    override suspend fun deleteChunksFromConversation(conversationId: String): Result<Unit> = runCatching {
        dao.deleteConversationChunks(conversationId)
    }

    override suspend fun createChunkFromMessages(
        conversationId: String,
        messages: List<MessageEntity>
    ): Result<MessageChunkEntity> = runCatching {

        if (messages.isEmpty()) throw IllegalStateException("Chunk must have at least 1 message")

        val startTs = messages.last().createdAt
        val endTs = messages.first().createdAt

        MessageChunkEntity(
            chunkId = UUID.randomUUID().toString(),
            conversationId = conversationId,
            startTimestamp = startTs,
            endTimestamp = endTs,
            messageIds = messages.map { it.messageId },
            count = messages.size
        )
    }

    override suspend fun saveOrUpdateChunk(chunk: MessageChunkEntity) {
        dao.insertChunk(chunk)
    }

    override suspend fun getChunks(conversationId: String, limit: Int): List<MessageChunkEntity> {
        return dao.getChunks(conversationId, limit)
    }
}
