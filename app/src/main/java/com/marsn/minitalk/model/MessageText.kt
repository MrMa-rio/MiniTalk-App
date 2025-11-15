package com.marsn.minitalk.model

data class MessageText(
    val text: String,
    val messageId: Long,
    val dateTimestamp: Long,
    val senderId: Long
) {
}