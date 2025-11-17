package com.marsn.minitalk.ui.feature.chat

import com.marsn.minitalk.core.domain.UserProfile


sealed interface MessageEvent {



    object Home : MessageEvent

    data class Chat(val conversationId: String) : MessageEvent
    data class Profile(val userProfile: UserProfile) : MessageEvent
}
