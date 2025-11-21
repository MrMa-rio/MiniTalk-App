package com.marsn.minitalk.core.dataprovider.repository.message

import com.marsn.minitalk.ui.feature.chat.conversation.MessageEventBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class MessageRepositoryImpl(
    private val messageDao: MessageDao
) : MessageRepository {


    override fun getMessages(conversationId: String): Flow<List<MessageEntity>> {
        return messageDao.getMessages(conversationId)
    }

    override suspend fun loadOlderMessages(
        conversationId: String,
        beforeTimestamp: Long,
        limit: Int
    ): Flow<List<MessageEntity>> {
        return messageDao.getOlderMessages(conversationId, beforeTimestamp, limit)
    }

    override suspend fun getLastMessage(conversationId: String): MessageEntity? {
        return messageDao.getLastMessage(conversationId)
    }


    override suspend fun sendMessage(message: MessageEntity) {
        messageDao.upsert(message.copy(isSent = false))
    }

    override suspend fun saveIncomingMessage(message: MessageEntity) {
        messageDao.upsert(message)
    }


    override suspend fun updateIsSent(messageId: Long, value: Boolean) {
        messageDao.updateIsSent(messageId, value)
    }

    override suspend fun updateIsDelivered(messageId: Long, value: Boolean) {
        messageDao.updateIsDelivered(messageId, value)
    }

    override suspend fun updateIsRead(messageId: Long, value: Boolean) {
        messageDao.updateIsRead(messageId, value)
    }

    override suspend fun updateIsDeleted(messageId: Long, value: Boolean) {
        messageDao.updateIsDeleted(messageId, value)
    }

    override suspend fun updateIsEdited(messageId: Long, value: Boolean) {
        messageDao.updateIsEdited(messageId, value)
    }

    override suspend fun deleteConversationMessages(conversationId: String) {
        messageDao.deleteByConversation(conversationId)
    }

}
