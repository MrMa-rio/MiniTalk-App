package com.marsn.minitalk.core.domain

import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationEntity
import com.marsn.minitalk.core.shared.enums.TypeConversation
import java.time.LocalDateTime


data class Conversation(
    val id: Long?,
    val userId: Long,
    val createdAt: LocalDateTime,
    val typeConversation: TypeConversation
) {
    fun toEntity(): ConversationEntity {
        id?.let {
            return ConversationEntity(
                id = id,
                userId = userId,
                createdAt = createdAt,
                typeConversation = typeConversation
            )
        }
        return ConversationEntity(
            userId = userId,
            createdAt = createdAt,
            typeConversation = typeConversation
        )
    }
}
