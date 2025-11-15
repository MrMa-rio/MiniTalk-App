package com.marsn.minitalk.dataprovider.repository.conversation

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversations")
data class ConversationEntity(
    @PrimaryKey val id: String,
    val participants: String, // JSON
    val lastMessage: String?,
    val lastTimestamp: Long,
    val type: String
)
