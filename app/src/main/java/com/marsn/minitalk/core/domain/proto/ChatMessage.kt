package com.marsn.minitalk.core.domain.proto

import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage(
    val messageId: Long,
    val conversationId: Long,
    val senderId: Long,
    val content: String,
    val timestamp: Long,
    val destinyId: Long,
    val isSent: Boolean = false,
    val isDelivered: Boolean = false,
    val isRead: Boolean = false,
    val isDeleted: Boolean = false,
    val isEdited: Boolean = false
)

