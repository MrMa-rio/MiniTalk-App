package com.marsn.minitalk.core.dataprovider.repository.message

import com.marsn.minitalk.core.dataprovider.clients.WebSocketManager
import com.marsn.minitalk.ui.feature.chat.conversation.MessageEventBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MessageRepositoryImpl(
    private val dao: MessageDao,
    private val manager: WebSocketManager,
    coroutineScope: CoroutineScope
) : MessageRepository {

    override suspend fun saveMessage(message: MessageEntity) {
        dao.insertMessage(message)
    }

    override suspend fun saveMessages(messages: List<MessageEntity>) {
        dao.insertMessages(messages)
    }

    override suspend fun getMessages(
        conversationId: String,
        limit: Int,
        offset: Int
    ): List<MessageEntity> {
        return dao.getMessagesPaged(conversationId, limit, offset)
    }

    override suspend fun countMessages(conversationId: String): Int {
        return dao.countMessages(conversationId)
    }

    init {
        coroutineScope.launch {
            MessageEventBus.events.collect { message ->
                dao.insertMessage(
                    MessageEntity(
                        conversationId = message.conversationId,
                        messageId = message.messageId,
                        senderId = message.senderId,
                        destinyId = message.destinyId,
                        content = message.content,
                        timestamp = message.timestamp,
                        createdAt = 0,
                        isRead = false,
                        isSent = false,
                        isDelivered = false,
                        isEdited = false,
                        isDeleted = false
                    )
                )
            }
        }
    }

    private fun listenAndStoreMessages() {
        CoroutineScope(Dispatchers.IO).launch {
            manager.messages.collect { incomingMsg ->
                dao.insertMessage(
                    MessageEntity(
                        conversationId = incomingMsg.conversationId,
                        messageId = incomingMsg.messageId,
                        senderId = incomingMsg.senderId,
                        destinyId = incomingMsg.destinyId,
                        content = incomingMsg.content,
                        timestamp = incomingMsg.timestamp,
                        createdAt = 0,
                        isRead = false,
                        isSent = false,
                        isDelivered = false,
                        isEdited = false,
                        isDeleted = false
                    )
                )
            }
        }
    }
}
