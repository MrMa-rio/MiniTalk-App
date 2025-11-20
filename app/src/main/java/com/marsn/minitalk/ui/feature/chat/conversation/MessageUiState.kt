package com.marsn.minitalk.ui.feature.chat.conversation

import com.marsn.minitalk.core.domain.MessageText
import com.marsn.minitalk.core.domain.contact.Contact
import com.marsn.minitalk.core.domain.proto.ChatMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MessageUiState(
    val userId: Long = 0,
    val inputText: String = "",
    val conversationId: Long = 0,
    val messages: List<MessageText> = emptyList(),
    val contact: Contact? = null,
    val isLoading: Boolean = true
) {
}