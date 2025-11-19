package com.marsn.minitalk.core.domain

import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationEntity
import com.marsn.minitalk.core.shared.enums.TypeConversation
import java.time.LocalDateTime


data class Conversation(
    val conversationId: Long?,
    val userId: List<Long>,
    val createdAt: LocalDateTime,
    val typeConversation: TypeConversation
) {
    fun toEntity(): ConversationEntity {
        conversationId?.let {
            return ConversationEntity(
                conversationId = conversationId,
                destinyId = userId,
                createdAt = createdAt,
                typeConversation = typeConversation
            )
        }
        return ConversationEntity(
            destinyId = userId,
            createdAt = createdAt,
            typeConversation = typeConversation
        )
    }
}
