package com.marsn.minitalk.core.dataprovider.repository.message.messageChunk

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marsn.minitalk.core.dataprovider.repository.message.MessageEntity
import java.util.UUID

@Entity(tableName = "message_chunks")
data class MessageChunkEntity(
    @PrimaryKey val chunkId: String,
    val conversationId: Long,
    val messageIds: List<String>,
    val startTimestamp: Long,
    val endTimestamp: Long,
    val count: Int
) {
    companion object {
        fun createFrom(messages: List<MessageEntity>): MessageChunkEntity {
            return MessageChunkEntity(
                chunkId = UUID.randomUUID().toString(),
                conversationId = messages.first().conversationId,
                messageIds = messages.map { it.messageId },
                startTimestamp = messages.minOf { it.timestamp },
                endTimestamp = messages.maxOf { it.timestamp },
                count = messages.size
            )
        }
    }
}
