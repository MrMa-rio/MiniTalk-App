package com.marsn.minitalk.ui.feature.chat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marsn.minitalk.core.dataprovider.repository.message.MessageRepository
import com.marsn.minitalk.core.domain.Message
import com.marsn.minitalk.core.usecase.message.SaveMessagesUseCase
import kotlinx.coroutines.launch

class ChatViewModel(
    private val saveMessagesUseCase: SaveMessagesUseCase,
    private val messageRepository: MessageRepository
) : ViewModel() {

    var uiState by mutableStateOf(ChatUiState())
        private set

    private var offset = 0
    private val limit = 30
    private var isLoading = false

    fun loadInitialMessages(conversationId: String) {
        viewModelScope.launch {
            val msgs = messageRepository.getMessages(conversationId, limit, 0)
            offset = msgs.size

            uiState = uiState.copy(messages = msgs)
        }
    }

    fun loadMoreMessages(conversationId: String) {
        if (isLoading) return
        isLoading = true

        viewModelScope.launch {
            val msgs = messageRepository.getMessages(conversationId, limit, offset)
            offset += msgs.size

            uiState = uiState.copy(messages = uiState.messages + msgs)
            isLoading = false
        }
    }

    fun receiveNewMessages(conversationId: String, incoming: List<Message>) {
        viewModelScope.launch {
            // salva + gera chunk
            saveMessagesUseCase(conversationId, incoming)

            // atualiza tela
            val added = incoming.map { it.toEntity() }
            uiState = uiState.copy(messages = added + uiState.messages)
        }
    }
}
