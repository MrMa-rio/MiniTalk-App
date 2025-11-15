package com.marsn.minitalk.domain

data class MessageText(
    val id: String,
    val senderId: String,
    val text: String?,
    val type: String,
    val mediaUrl: String?,
    val timestamp: Long,
    val status: String,
    val isDeleted: Boolean,
    val isEdited: Boolean
)
