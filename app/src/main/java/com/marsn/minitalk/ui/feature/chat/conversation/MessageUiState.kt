package com.marsn.minitalk.ui.feature.chat.conversation

import com.marsn.minitalk.core.domain.contact.Contact

data class MessageUiState(
    val userId: Long = 0,
    val inputText: String = "",
    val contact: Contact? = null,
    val isLoading: Boolean = true
) {
}