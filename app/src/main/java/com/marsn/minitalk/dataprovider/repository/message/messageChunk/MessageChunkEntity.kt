package com.marsn.minitalk.dataprovider.repository.message.messageChunk

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "message_chunks",
    indices = [Index(value = ["conversationId", "chunkNumber"])]
)
data class MessageChunkEntity(
    @PrimaryKey val chunkId: String,
    val conversationId: String,
    val startTimestamp: Long,
    val endTimestamp: Long,
    val messagesJson: String  // JSON contendo várias mensagens
)
