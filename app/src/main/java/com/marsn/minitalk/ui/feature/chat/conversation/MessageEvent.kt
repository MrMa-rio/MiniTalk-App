package com.marsn.minitalk.ui.feature.chat.conversation

import com.marsn.minitalk.core.domain.UserProfile


sealed interface MessageEvent {
    object Home : MessageEvent
    data class Profile(val userProfile: UserProfile) : MessageEvent
    data class InputText(val text: String) : MessageEvent

    object Send : MessageEvent
}
