package com.marsn.minitalk.model

data class Message(
    val conversationId: Long,
    val senderId: Long,
    val text: String,
    val timestamp: Long
) {
}