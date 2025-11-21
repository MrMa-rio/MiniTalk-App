package com.marsn.minitalk.core.usecase.message

import com.marsn.minitalk.core.dataprovider.clients.WebSocketManager
import com.marsn.minitalk.core.dataprovider.repository.message.MessageEntity
import com.marsn.minitalk.core.dataprovider.repository.message.MessageRepository
import com.marsn.minitalk.core.domain.MessageText
import com.marsn.minitalk.core.domain.proto.ChatMessage
import com.marsn.minitalk.core.shared.enums.TypeContent
import com.marsn.minitalk.core.shared.enums.TypeConversation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MessagesUseCaseImpl(
    private val messageRepository: MessageRepository,
    private val socketManager: WebSocketManager
) : MessagesUseCase {
    override suspend fun sendMessage(message: ChatMessage) {

        messageRepository.saveIncomingMessage(
            MessageEntity(
                messageId = message.messageId,
                conversationId = message.conversationId,
                senderId = message.senderId,
                content = message.content,
                typeContent = TypeContent.TEXT,
                timestamp = message.timestamp,
                isSent = message.isSent,
                isDelivered = message.isDelivered,
                isRead = message.isRead,
                isDeleted = message.isDeleted,
                isEdited = message.isEdited,
            )
        )
        socketManager.send(message)
    }

    override suspend fun consultMessages(conversationId: String): Flow<List<MessageText>> {
        return messageRepository.getMessages(conversationId).map {
            it.map { messageEntity ->
                MessageText(
                    id = messageEntity.id,
                    senderId = messageEntity.senderId,
                    text = messageEntity.content,
                    timestamp = messageEntity.timestamp,
                    isSent = messageEntity.isSent,
                    isDelivered = messageEntity.isDelivered,
                    isRead = messageEntity.isRead,
                    isDeleted = messageEntity.isDeleted,
                    isEdited = messageEntity.isEdited,
                    type = TypeConversation.PRIVATE.name
                )
            }
        }
    }

    override suspend fun consultOlderMessages(
        conversationId: String,
        timestamp: Long,
        limit: Int
    ): Flow<List<MessageText>> {
        return messageRepository.loadOlderMessages(
            conversationId,
            limit = limit,
            beforeTimestamp = timestamp
        ).map {
            it.map { messageEntity ->
                MessageText(
                    id = messageEntity.id,
                    senderId = messageEntity.senderId,
                    text = messageEntity.content,
                    timestamp = messageEntity.timestamp,
                    isSent = messageEntity.isSent,
                    isDelivered = messageEntity.isDelivered,
                    isRead = messageEntity.isRead,
                    isDeleted = messageEntity.isDeleted,
                    isEdited = messageEntity.isEdited,
                    type = TypeConversation.PRIVATE.name
                )
            }
        }
    }
}


