package com.marsn.minitalk.ui.feature.home

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationRepositoryImpl
import com.marsn.minitalk.core.domain.Conversation
import com.marsn.minitalk.core.shared.enums.TypeConversation
import com.marsn.minitalk.core.usecase.conversation.ConversationUsecase
import com.marsn.minitalk.ui.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ConversationViewModel(
    val conversationUsecase: ConversationUsecase
) : ViewModel() {

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val conversations = conversationUsecase.consultAllConversations()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun createConversation() {
        viewModelScope.launch {
            conversationUsecase.createConversation(Conversation(
                100,
                1000,
                "Hello World!",
                1526474894,
                TypeConversation.PRIVATE
            ))
        }

    }

}