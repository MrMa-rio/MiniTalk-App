package com.marsn.minitalk.ui.feature.chat.conversation

import com.marsn.minitalk.core.dataprovider.repository.message.MessageEntity

data class ChatUiState(
    val messages: List<MessageEntity> = emptyList(),
    val isLoading: Boolean = false
)
