package com.marsn.minitalk.core.dataprovider.repository.conversation

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marsn.minitalk.core.domain.Conversation
import com.marsn.minitalk.core.shared.enums.TypeConversation

@Entity(tableName = "conversations")
data class ConversationEntity(
    @PrimaryKey val id: Long,
    val conversationId: Long,
    val lastMessage: String?,
    val lastTimestamp: Long,
    val typeConversation: TypeConversation

) {
    fun toModel(): Conversation {
        return Conversation(
            id = id,
            conversationId = conversationId,
            lastMessage = lastMessage.orEmpty(),
            lastTimestamp = lastTimestamp,
            typeConversation = typeConversation,
        )
    }
}
