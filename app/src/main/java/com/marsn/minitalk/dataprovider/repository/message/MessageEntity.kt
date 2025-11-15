package com.marsn.minitalk.dataprovider.repository.message

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marsn.minitalk.domain.Message

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey val messageId: String,
    val conversationId: String,
    val senderId: String,
    val content: String,
    val createdAt: Long,
    val timestamp: Long,
    val isSent: Boolean,
    val isDelivered: Boolean,
    val isRead: Boolean,
    val isDeleted: Boolean,
    val isEdited: Boolean
) {

    fun toModel() : Message {
        return Message(
            id = messageId,
            conversationId = conversationId,
            senderId = senderId,
            content = content,
            createdAt = createdAt,
            isSent = isSent,
            isDelivered = isDelivered,
            isRead = isRead,
            isDeleted = isDeleted,
            isEdited = isEdited
        )
    }

}
