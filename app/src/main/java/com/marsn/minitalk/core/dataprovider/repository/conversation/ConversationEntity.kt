package com.marsn.minitalk.core.dataprovider.repository.conversation

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marsn.minitalk.core.domain.Conversation
import com.marsn.minitalk.core.shared.enums.TypeConversation
import java.time.LocalDateTime

@Entity(tableName = "conversations")
data class ConversationEntity(
    @PrimaryKey(autoGenerate = true) val conversationId: Long = 0,
    val destinyId: List<Long>,
    val createdAt: LocalDateTime,
    val typeConversation: TypeConversation

) {
    fun toModel(): Conversation {
        return Conversation(
            conversationId = conversationId,
            userId = destinyId,
            createdAt = createdAt,
            typeConversation = typeConversation,
        )
    }
}
