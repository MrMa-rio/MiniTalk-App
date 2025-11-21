package com.marsn.minitalk.core.domain.conversation

data class ConversationPreview(
    val conversationId: Long,
    val title: String,
    val avatarUrl: String?,
    val lastMessage: String?,
    val lastMessageTimestamp: Long?,
    val unreadCount: Int,
    val isGroup: Boolean
)