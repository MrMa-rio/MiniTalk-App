package com.marsn.minitalk.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marsn.minitalk.core.domain.Conversation
import com.marsn.minitalk.core.shared.enums.TypeConversation
import com.marsn.minitalk.core.usecase.conversation.ConversationUsecase
import com.marsn.minitalk.ui.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID

class ConversationViewModel(
   private val conversationUsecase: ConversationUsecase,
) : ViewModel() {

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _conversations = MutableStateFlow<List<Conversation>>(emptyList())
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    val conversations = _searchText
        .combine(_conversations) { text, conversations ->
            if(text.isBlank()) {
                conversations
            } else {
                conversations.filter {
                    // sua lógica de filtro aqui
                    it.lastMessage.contains(text, ignoreCase = true)
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _conversations.value
        )

    init {
        getAllConversations()
    }

    fun createConversation() {
        viewModelScope.launch {
            conversationUsecase.createConversation(
                Conversation(
                    id = null,
                    conversationId = UUID.randomUUID().toString(),
                    lastMessage = "Hello World!",
                    lastTimestamp = 1526474894,
                    typeConversation = TypeConversation.PRIVATE
                )
            )
        }
    }

    private fun getAllConversations() {
        conversationUsecase.consultAllConversations()
            .onEach { conversationList ->
                _conversations.value = conversationList
            }
            .launchIn(viewModelScope)
    }
}
