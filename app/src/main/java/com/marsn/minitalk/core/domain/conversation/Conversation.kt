package com.marsn.minitalk.core.domain.conversation

import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationEntity
import com.marsn.minitalk.core.shared.enums.TypeConversation

data class Conversation(
    val conversationId: Long,
    val createdAt: Long,
    val typeConversation: TypeConversation
) {
    fun toEntity(): ConversationEntity {
        return ConversationEntity(
            conversationId = conversationId,
            createdAt = createdAt,
            typeConversation = typeConversation
        )
    }
}