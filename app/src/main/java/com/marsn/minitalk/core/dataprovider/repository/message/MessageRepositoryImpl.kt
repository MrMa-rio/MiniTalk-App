package com.marsn.minitalk.core.dataprovider.repository.message


class MessageRepositoryImpl(
    private val dao: MessageDao
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
}
