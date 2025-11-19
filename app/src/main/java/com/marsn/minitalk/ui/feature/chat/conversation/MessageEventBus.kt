package com.marsn.minitalk.ui.feature.chat.conversation

import com.marsn.minitalk.core.domain.proto.ChatMessage
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object MessageEventBus {
    private val _events = MutableSharedFlow<ChatMessage>()
    val events = _events.asSharedFlow()

    suspend fun emit(message: ChatMessage) {
        _events.emit(message)
    }
}
