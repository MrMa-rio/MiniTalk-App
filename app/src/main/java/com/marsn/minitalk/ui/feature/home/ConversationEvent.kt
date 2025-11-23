package com.marsn.minitalk.ui.feature.home

import com.marsn.minitalk.core.domain.UserProfile
import com.marsn.minitalk.core.shared.enums.TypeConversation


sealed interface ConversationEvent {



    object Home : ConversationEvent

    data class Chat(val conversationId: String, val typeConversation: TypeConversation) : ConversationEvent
    data class Profile(val userProfile: UserProfile) : ConversationEvent

    data class Tab(val tab: Int) : ConversationEvent

    data class SearchText(val text: String) : ConversationEvent
    object GetConversations: ConversationEvent

}
