package com.marsn.minitalk.core.domain

data class MessageText(
    val id: Long,
    val senderId: Long,
    val text: String?,
    val type: String,
    val mediaUrl: String?,
    val timestamp: Long,
    val status: String,
    val isDeleted: Boolean,
    val isEdited: Boolean
)
