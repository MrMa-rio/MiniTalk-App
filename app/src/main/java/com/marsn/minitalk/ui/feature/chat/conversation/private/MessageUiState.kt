package com.marsn.minitalk.ui.feature.chat.conversation.private

import com.marsn.minitalk.core.domain.MessageText
import com.marsn.minitalk.core.domain.UserSession
import com.marsn.minitalk.core.domain.contact.Contact
import com.marsn.minitalk.core.domain.conversation.Conversation
import com.marsn.minitalk.core.shared.enums.TypeConversation
import com.marsn.minitalk.ui.feature.chat.conversation.ContentHeader

data class MessageUiState(
    val userSession: UserSession? = null,
    val inputText: String = "",
    val conversation: Conversation? = null,
    val typeConversation: TypeConversation? = null,
    val messages: List<MessageText> = emptyList(),
    val contact: Contact? = null,
    val isLoading: Boolean = true,
    val contentHeader: ContentHeader? = null
) {
}