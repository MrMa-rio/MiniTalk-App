package com.marsn.minitalk.core.mapper

import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationEntity
import com.marsn.minitalk.core.domain.Conversation

fun ConversationEntity.toModel(): Conversation {
    return Conversation(
        id = this.id,
        conversationId = this.conversationId,
        lastMessage = this.lastMessage,
        lastTimestamp = this.lastTimestamp,
        typeConversation = this.typeConversation
    )
}
