package com.marsn.minitalk.core.domain

import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationEntity
import com.marsn.minitalk.core.shared.enums.TypeConversation
import java.time.LocalDateTime


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
