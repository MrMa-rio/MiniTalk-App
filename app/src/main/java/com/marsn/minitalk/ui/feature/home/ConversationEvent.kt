package com.marsn.minitalk.ui.feature.home

import com.marsn.minitalk.core.domain.UserProfile


sealed interface ConversationEvent {



    object Home : ConversationEvent

    data class Chat(val conversationId: Long) : ConversationEvent
    data class Profile(val userProfile: UserProfile) : ConversationEvent

    data class Tab(val tab: Int) : ConversationEvent

    data class SearchText(val text: String) : ConversationEvent


}
