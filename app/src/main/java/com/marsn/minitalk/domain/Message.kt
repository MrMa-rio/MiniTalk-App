package com.marsn.minitalk.domain

import com.marsn.minitalk.dataprovider.repository.message.MessageEntity

data class Message(
    val id: String,
    val conversationId: String,
    val senderId: String,
    val content: String,
    val createdAt: Long,
    val isDelivered: Boolean = false,
    val isRead: Boolean = false,
    val isSent: Boolean = false,
    val isEdited: Boolean = false,
    val isDeleted: Boolean = false
) {
    fun toEntity(): MessageEntity {
        return MessageEntity(
            messageId = id,
            conversationId = conversationId,
            createdAt = createdAt,
            senderId = senderId,
            content = content,
            isRead = isRead,
            isDelivered = isDelivered,
            isSent = isSent,
            isEdited = isEdited,
            isDeleted = isDeleted
        )
    }

}
