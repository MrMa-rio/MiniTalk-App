package com.marsn.minitalk.core.dataprovider.middleware

import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationRepository
import com.marsn.minitalk.core.dataprovider.repository.message.MessageEntity
import com.marsn.minitalk.core.dataprovider.repository.message.MessageRepository
import com.marsn.minitalk.core.dataprovider.repository.userSession.UserSessionRepository
import com.marsn.minitalk.core.domain.proto.ChatMessage
import com.marsn.minitalk.core.shared.enums.TypeContent
import kotlinx.coroutines.flow.first

class MessageMiddlewareImpl(
    private val messageRepository: MessageRepository, //CHAMARA O REPOSITORY
    private val conversationRepository: ConversationRepository, //CHAMARA O REPOSITORY
    private val userRepository: UserSessionRepository //CHAMARA O REPOSITORY //NAO DEPENDA MAIS DE USECASE
) : MessageMiddleware {
    override suspend fun onMessage(msg: ChatMessage) {

        val entity = MessageEntity(
            messageId = msg.messageId,
            conversationId = msg.conversationId,
            senderId = msg.senderId,
            content = msg.content,
            typeContent = TypeContent.TEXT,
            timestamp = msg.timestamp,
            isDelivered = msg.isDelivered,
            isSent = msg.isSent,
            isRead = msg.isRead,
            isDeleted = msg.isDeleted,
            isEdited = msg.isEdited,
        )


        val currentUser = userRepository.getUserSessionFlow().first()

       currentUser?.let {
           val conversation = conversationRepository.getConversationByParticipantId(currentUser.userId)
           conversation ?: conversationRepository.createPrivateConversation(currentUser.userId, msg.senderId)
               .toModel()

           messageRepository.saveIncomingMessage(entity)
       }
    }
}
