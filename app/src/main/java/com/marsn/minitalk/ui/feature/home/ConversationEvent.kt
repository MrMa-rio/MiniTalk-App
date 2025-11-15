package com.marsn.minitalk.ui.feature.home

import com.marsn.minitalk.model.Contact
import com.marsn.minitalk.model.UserProfile


sealed interface ConversationEvent {



    object Home : ConversationEvent

    data class Chat(val conversationId: Long, val contact: Contact) : ConversationEvent

    object  NewConversation : ConversationEvent
    data class Profile(val userProfile: UserProfile) : ConversationEvent

    data class Tab(val tab: Int) : ConversationEvent

    data class SearchText(val text: String) : ConversationEvent


}
