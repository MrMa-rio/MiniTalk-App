package com.marsn.minitalk.core.domain.conversation

import com.marsn.minitalk.core.shared.enums.TypeContent
import com.marsn.minitalk.core.shared.enums.TypeConversation

data class ConversationItem(
    val conversationId: String,
    val typeConversation: TypeConversation,
    val participantName: String,
    val participantAvatar: String?,
    val lastMessage: String?,
    val lastMessageType: TypeContent?,
    val lastMessageTimestamp: Long?,
    val isRead: Boolean?
)
