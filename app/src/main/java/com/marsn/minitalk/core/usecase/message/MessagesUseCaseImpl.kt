package com.marsn.minitalk.core.usecase.message

import com.marsn.minitalk.core.dataprovider.clients.WebSocketManager
import com.marsn.minitalk.core.dataprovider.repository.message.MessageEntity
import com.marsn.minitalk.core.dataprovider.repository.message.MessageRepository
import com.marsn.minitalk.core.domain.MessageText
import com.marsn.minitalk.core.domain.proto.ChatMessage
import com.marsn.minitalk.core.shared.enums.TypeConversation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MessagesUseCaseImpl(
    private val messageRepository: MessageRepository,
    private val socketManager: WebSocketManager
) : MessagesUseCase {
    override suspend fun sendMessage(message: ChatMessage) {

        messageRepository.saveMessage(
            MessageEntity(
                messageId = message.messageId,
                conversationId = message.conversationId,
                senderId = message.senderId,
                destinyId = message.destinyId,
                content = message.content,
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

    override suspend fun consultMessages(conversationId: Long): Flow<List<MessageText>> {
        return messageRepository.getLatestMessages(conversationId).map {
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
        conversationId: Long,
        timestamp: Long,
        limit: Int
    ): Flow<List<MessageText>> {
        return messageRepository.getOlderMessages(conversationId, limit, timestamp).map {
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


