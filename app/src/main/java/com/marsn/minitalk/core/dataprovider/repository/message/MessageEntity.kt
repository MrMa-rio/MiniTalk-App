package com.marsn.minitalk.core.dataprovider.repository.message

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marsn.minitalk.core.domain.Message
import kotlinx.serialization.Serializable

@Entity(tableName = "messages")
@Serializable
data class MessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val messageId: Long,
    val conversationId: Long,
    val senderId: Long,
    val destinyId: Long,
    val content: String,
    val timestamp: Long,
    val isSent: Boolean,
    val isDelivered: Boolean,
    val isRead: Boolean,
    val isDeleted: Boolean,
    val isEdited: Boolean
) {

    fun toModel() : Message {
        return Message(
            id = id,
            messageId = messageId,
            conversationId = conversationId,
            senderId = senderId,
            destinyId = destinyId,
            content = content,
            timestamp = timestamp,
            isSent = isSent,
            isDelivered = isDelivered,
            isRead = isRead,
            isDeleted = isDeleted,
            isEdited = isEdited,
        )
    }

}
