package com.marsn.minitalk.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marsn.minitalk.core.domain.Conversation
import com.marsn.minitalk.core.shared.enums.TypeConversation
import com.marsn.minitalk.core.usecase.conversation.ConversationUsecase
import com.marsn.minitalk.ui.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ConversationViewModel(
    val conversationUsecase: ConversationUsecase
) : ViewModel() {

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val conversations: StateFlow<List<Conversation>> =
        conversationUsecase.consultAllConversations()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun createConversation() {
        viewModelScope.launch {
            conversationUsecase.createConversation(
                Conversation(
                    id = 100,
                    conversationId = 1000,
                    lastMessage = "Hello World!",
                    lastTimestamp = 1526474894,
                    typeConversation = TypeConversation.PRIVATE
                )
            )
        }
    }

    fun getAllConversations() {
        conversationUsecase.consultAllConversations()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
    }
}
