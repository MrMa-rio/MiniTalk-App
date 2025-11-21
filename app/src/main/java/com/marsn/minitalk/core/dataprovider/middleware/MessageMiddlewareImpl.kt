package com.marsn.minitalk.core.dataprovider.middleware

import com.marsn.minitalk.core.domain.proto.ChatMessage
import com.marsn.minitalk.core.usecase.conversation.ConversationUsecase
import com.marsn.minitalk.core.usecase.message.MessagesUseCase
import com.marsn.minitalk.core.usecase.users.UserSessionUsecase

class MessageMiddlewareImpl(
    private val messagesUseCase: MessagesUseCase, //CHAMARA O REPOSITORY
    private val conversationUsecase: ConversationUsecase, //CHAMARA O REPOSITORY
    private val userSessionUsecase: UserSessionUsecase //CHAMARA O REPOSITORY //NAO DEPENDA MAIS DE USECASE
) : MessageMiddleware {
    override suspend fun onMessage(msg: ChatMessage) {

        val currentUser = userSessionUsecase.consultUserSession()

       currentUser?.let {
           conversationUsecase.consultOrCreateUserConversation(currentUser.userId, msg.senderId)

           messagesUseCase.saveMessage(msg)
       }
    }
}
