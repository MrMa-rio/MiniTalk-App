package com.marsn.minitalk.core.dataprovider.repository.message

import com.marsn.minitalk.ui.feature.chat.conversation.MessageEventBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class MessageRepositoryImpl(
    private val dao: MessageDao
) : MessageRepository {


    override suspend fun saveMessage(message: MessageEntity) {
        dao.insertMessage(message)
    }

    override suspend fun saveMessages(messages: List<MessageEntity>) {
        dao.insertMessages(messages)
    }

    override suspend fun getLatestMessages(
        conversationId: Long,
        limit: Int
    ): Flow<List<MessageEntity>> {
        return dao.getLatestMessages(conversationId, limit)
    }

    override suspend fun getOlderMessages(
        conversationId: Long,
        limit: Int,
        timestamp: Long
    ): Flow<List<MessageEntity>> {
        return dao.getOlderMessages(conversationId, timestamp, limit)
    }
}
