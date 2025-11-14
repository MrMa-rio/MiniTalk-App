package com.marsn.minitalk.ui.feature.home

import com.marsn.minitalk.model.UserProfile


sealed interface ConversationEvent {



    data class Chat(val conversationId: Long) : ConversationEvent
    data class Profile(val userProfile: UserProfile) : ConversationEvent
}
