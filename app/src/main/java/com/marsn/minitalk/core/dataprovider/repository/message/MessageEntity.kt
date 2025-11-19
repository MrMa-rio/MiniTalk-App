package com.marsn.minitalk.core.dataprovider.repository.message

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marsn.minitalk.core.domain.Message
import kotlinx.serialization.Serializable

@Entity(tableName = "messages")
@Serializable
data class MessageEntity(
    @PrimaryKey
    val messageId: String,
    val conversationId: Long,
    val senderId: Long,
    val destinyId: Long,
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
            destinyId = destinyId,
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
