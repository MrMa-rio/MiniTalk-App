package com.marsn.minitalk.ui.feature.chat.conversation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marsn.minitalk.core.dataprovider.clients.WebSocketManager
import com.marsn.minitalk.core.domain.proto.ChatMessage
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
import java.time.LocalDateTime
import java.util.UUID
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class MessagingViewModel(
    val contactUsecase: ContactUsecase,
    private val socketManager: WebSocketManager
) : ViewModel() {

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private val _uiState = MutableStateFlow(MessageUiState())
    val uiState = _uiState.asStateFlow()





    fun loadContact(userId: Long) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val contactFlow = contactUsecase.consultContact(userId)
            _uiState.update { currentState ->
                currentState.copy(
                    contact = contactFlow,
                    isLoading = false
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
                    sendMock(100, 200, socketManager)
                    _uiState.value = _uiState.value.copy(
                        inputText = ""
                    )
                }
            }
        }
    }
    fun sendMock(senderId: Long, destinyId: Long, client: WebSocketManager) {

        val message = ChatMessage(
            messageId = UUID.randomUUID().toString(),
            conversationId = 115,
            senderId = senderId,
            content = uiState.value.inputText,
            timestamp = 15252145,
            destinyId = destinyId
        )
        client.send(message)
    }
}