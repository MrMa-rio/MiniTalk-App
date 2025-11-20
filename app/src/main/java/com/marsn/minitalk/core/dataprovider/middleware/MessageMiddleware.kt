package com.marsn.minitalk.core.dataprovider.middleware

import com.marsn.minitalk.core.dataprovider.repository.message.MessageEntity
import com.marsn.minitalk.core.dataprovider.repository.message.MessageRepository
import com.marsn.minitalk.core.domain.proto.ChatMessage

class MessageMiddleware(
    private val repository: MessageRepository
) {
    suspend fun onMessage(msg: ChatMessage) {
        val entity = MessageEntity(
            messageId = msg.messageId,
            conversationId = msg.conversationId,
            senderId = msg.senderId,
            destinyId = msg.destinyId,
            content = msg.content,
            timestamp = msg.timestamp,
            isSent = msg.isSent,
            isDelivered = msg.isDelivered,
            isRead = msg.isRead,
            isDeleted = msg.isDeleted,
            isEdited = msg.isEdited
        )

        repository.saveMessage(entity)
    }
}
