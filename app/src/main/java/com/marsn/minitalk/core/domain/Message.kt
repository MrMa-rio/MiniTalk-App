package com.marsn.minitalk.core.domain

import com.marsn.minitalk.core.dataprovider.repository.message.MessageEntity

data class Message(
    val id: Long,
    val messageId: Long,
    val conversationId: Long,
    val senderId: Long,
    val destinyId: Long,
    val timestamp: Long,
    val content: String,
    val isDelivered: Boolean = false,
    val isRead: Boolean = false,
    val isSent: Boolean = false,
    val isEdited: Boolean = false,
    val isDeleted: Boolean = false
) {
    fun toEntity(): MessageEntity {
        return MessageEntity(
            id = id,
            messageId = messageId,
            conversationId = conversationId,
            senderId = senderId,
            destinyId = destinyId,
            content = content,
            isRead = isRead,
            isDelivered = isDelivered,
            isSent = isSent,
            isEdited = isEdited,
            isDeleted = isDeleted,
            timestamp = timestamp
        )
    }

}
