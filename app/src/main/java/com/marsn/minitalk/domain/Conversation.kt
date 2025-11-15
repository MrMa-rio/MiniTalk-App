package com.marsn.minitalk.domain


data class Conversation(
    val id: String,
    val members: List<String>,
    val lastMessage: Message?,
    val updatedAt: Long
)
