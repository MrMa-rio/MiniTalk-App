package com.marsn.minitalk.ui.feature.chat.conversation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marsn.minitalk.core.domain.proto.ChatMessage
import com.marsn.minitalk.core.usecase.conversation.ConversationUsecase
import com.marsn.minitalk.core.usecase.message.MessagesUseCase
import com.marsn.minitalk.core.usecase.users.UserSessionUsecase
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
    val messagesUseCase: MessagesUseCase,
    val conversationUsecase: ConversationUsecase,
    val userSessionUsecase: UserSessionUsecase,

) : ViewModel() {

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private val _uiState = MutableStateFlow(MessageUiState())
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            loadUserSession()
            loadContentChat()
            val messages = messagesUseCase.consultMessages(
                uiState.value.conversation?.conversationId
                    ?: ""
            )
            _uiState.value = _uiState.value.copy(
                messages = messages.stateIn(
                    viewModelScope,
                    SharingStarted.WhileSubscribed(5000),
                    _uiState.value.messages
                ).value
            )
        }
    }

    private fun loadContentChat() {

        val conversation = _uiState.value.conversation

        _uiState.value = _uiState.value.copy(
        contentHeader = ContentHeader(
            "TEESTE",
            "",
            conversation?.conversationId ?: "",
            "Eu, Voce"
        )
        )
    }

    private fun observeMessages(conversationId: String) {
        viewModelScope.launch {
            messagesUseCase.consultMessages(conversationId)
                .collect { msgs ->
                    _uiState.update { it.copy(messages = msgs) }
                }
        }
    }

    fun loadConversationAndParticipants(conversationId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val conversation = conversationUsecase.consultConversation(conversationId)
            val participants = conversationUsecase.consultParticipantsByConversationId(conversationId)
            _uiState.update {
                it.copy(
                    conversation = conversation,
                    typeConversation = conversation?.typeConversation,
                    participants = participants,
                    isLoading = false,
                )
            }
            observeMessages(conversationId)
        }
    }

    suspend fun loadUserSession() {
        val userSession = userSessionUsecase.consultUserSession()
        _uiState.update { it.copy(userSession = userSession) }
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
                    sendingMessage()
                    _uiState.value = _uiState.value.copy(
                        inputText = ""
                    )
                }
            }
        }
    }

    private suspend fun sendingMessage() {
        val currentUser = uiState.value.userSession?.userId ?: 0
        val participants = uiState.value.participants

        participants.map {
            if (it.userId != currentUser) {
                sendMock(currentUser, it.userId)
            }
        }
    }

    suspend fun sendMock(senderId: Long, destinyId: Long) {

        val message = ChatMessage(
            messageId = System.currentTimeMillis() / 365, //TODO: MELHORAR A FORMA DE CRIACAO DA MENSAGEMID
            conversationId = uiState.value.conversation?.conversationId ?: "",
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

    fun loadOlderMessages(conversationId: String, timestamp: Long) {
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