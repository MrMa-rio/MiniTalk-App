package com.marsn.minitalk.ui.feature.chat.conversation

import com.marsn.minitalk.core.domain.contact.Contact
import kotlinx.coroutines.flow.StateFlow


data class ChatUiState(
    val userId: Long = 0,
    val inputText: String = "",
    val contact: Contact? = null,
    val isLoading: Boolean = true
) {



}
