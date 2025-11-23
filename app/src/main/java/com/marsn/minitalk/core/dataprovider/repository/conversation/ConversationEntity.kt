package com.marsn.minitalk.core.dataprovider.repository.conversation

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marsn.minitalk.core.domain.conversation.Conversation
import com.marsn.minitalk.core.shared.enums.TypeConversation
import java.time.LocalDateTime

@Entity(tableName = "conversations")
data class ConversationEntity(
    @PrimaryKey() val conversationId: String,
    val createdAt: Long,
    val typeConversation: TypeConversation

) {
    fun toModel(): Conversation {
        return Conversation(
            conversationId = conversationId,
            createdAt = createdAt,
            typeConversation = typeConversation,
        )
    }
}
