package com.marsn.minitalk.ui.feature.chat.conversation

import com.marsn.minitalk.core.domain.MessageText
import com.marsn.minitalk.core.domain.contact.Contact
import com.marsn.minitalk.core.domain.proto.ChatMessage

data class MessageUiState(
    val userId: Long = 0,
    val inputText: String = "",
    val messages: List<MessageText> = emptyList(),
    val contact: Contact? = null,
    val isLoading: Boolean = true
) {
}