package com.marsn.minitalk.ui.feature.home

import com.marsn.minitalk.core.domain.conversation.Conversation
import com.marsn.minitalk.core.domain.contact.Contact
import com.marsn.minitalk.core.domain.conversation.ConversationItem

data class HomeUiState(
    val userId: Long = 0,
    val searchText: String = "",
    val conversations: List<ConversationItem> = emptyList(),
    val contact: Contact? = null,
    val isLoading: Boolean = true
) {



}