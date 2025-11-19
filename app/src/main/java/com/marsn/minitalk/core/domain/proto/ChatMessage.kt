package com.marsn.minitalk.core.domain.proto

import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage(
    val messageId: String,
    val conversationId: Long,
    val senderId: Long,
    val content: String,
    val timestamp: Long,
    val destinyId: Long
)

