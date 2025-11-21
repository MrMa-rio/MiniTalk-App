package com.marsn.minitalk.ui.feature.chat.conversation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marsn.minitalk.core.domain.proto.ChatMessage
import com.marsn.minitalk.core.usecase.conversation.ConversationUsecase
import com.marsn.minitalk.core.usecase.message.MessagesUseCase
import com.marsn.minitalk.core.usecase.users.ContactUsecase
import com.marsn.minitalk.navigation.ChatRoutes
import com.marsn.minitalk.ui.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MessagingViewModel(
    val contactUsecase: ContactUsecase,
    val messagesUseCase: MessagesUseCase,
    val conversationUsecase: ConversationUsecase

) : ViewModel() {

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private val _uiState = MutableStateFlow(MessageUiState())
    val uiState = _uiState.asStateFlow()


    init {
        observeMessages()
    }

    private fun observeMessages() {
        viewModelScope.launch {
            messagesUseCase.consultMessages(uiState.value.conversation?.conversationId ?: 0)
                .collect { msgs ->
                    _uiState.update { it.copy(messages = msgs) }
                }
        }
    }

    fun loadConversation(conversationId: Long) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val conversation = conversationUsecase.consultConversation(conversationId)
            _uiState.update { currentState ->
                currentState.copy(
                    conversation = conversation,
                    isLoading = false,
                )
            }
        }
    }


    fun onEvent(event: MessageEvent) {
        when (event) {

            is MessageEvent.Profile -> {
                viewModelScope.launch {
                    _uiEvent.send(
                        UIEvent.NavigateToProfile(
                            ChatRoutes.ProfileRoute,
                            event.userProfile
                        )
                    )
                }
            }

            is MessageEvent.Home -> {
                viewModelScope.launch {
                    _uiEvent.send(UIEvent.NavigateBack)
                }
            }

            is MessageEvent.InputText -> {
                viewModelScope.launch {
                    _uiState.value = _uiState.value.copy(
                        inputText = event.text
                    )
                }
            }

            is MessageEvent.Send -> {
                viewModelScope.launch {
                    sendMock(10, 3)
                    _uiState.value = _uiState.value.copy(
                        inputText = ""
                    )
                }
            }
        }
    }

    suspend fun sendMock(senderId: Long, destinyId: Long) {

        val message = ChatMessage(
            messageId = 101,
            conversationId = uiState.value.conversation?.conversationId ?: 0,
            senderId = senderId,
            content = uiState.value.inputText,
            timestamp = System.currentTimeMillis(),
            destinyId = destinyId,
            isSent = false,
            isDelivered = false,
            isRead = false,
            isDeleted = false,
            isEdited = false
        )
        messagesUseCase.sendMessage(message)
    }

    fun loadOlderMessages(conversationId: Long, timestamp: Long) {
        viewModelScope.launch {
            val older = messagesUseCase.consultOlderMessages(conversationId, timestamp).stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                _uiState.value.messages
            ).value

            _uiState.update { state ->
                state.copy(
                    messages = state.messages + older
                )
            }
        }
    }

}