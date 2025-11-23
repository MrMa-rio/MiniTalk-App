package com.marsn.minitalk.core.domain

data class MessageText(
    val id: Long,
    val senderId: Long,
    val text: String?,
    val type: String,
    val timestamp: Long,
    val isSent: Boolean,
    val isDelivered: Boolean,
    val isRead: Boolean,
    val isDeleted: Boolean,
    val isEdited: Boolean
)
