package com.marsn.minitalk.core.domain

import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationEntity
import com.marsn.minitalk.core.shared.enums.TypeConversation


data class Conversation(
    val id: Long,
    val conversationId: Long,
    val lastMessage: String?,
    val lastTimestamp: Long,
    val typeConversation: TypeConversation
) {
    fun toEntity(): ConversationEntity {
        return ConversationEntity(
            id = id,
            conversationId = conversationId,
            lastMessage = lastMessage,
            lastTimestamp = lastTimestamp,
            typeConversation = typeConversation
        )
    }
}
