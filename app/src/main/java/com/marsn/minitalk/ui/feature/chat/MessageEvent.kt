package com.marsn.minitalk.ui.feature.chat

import com.marsn.minitalk.model.UserProfile


sealed interface MessageEvent {



    object Home : MessageEvent

    data class Chat(val conversationId: Long) : MessageEvent
    data class Profile(val userProfile: UserProfile) : MessageEvent
}
