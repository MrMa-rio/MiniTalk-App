package com.marsn.minitalk.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marsn.minitalk.core.usecase.users.ContactUsecase
import com.marsn.minitalk.navigation.ChatRoutes
import com.marsn.minitalk.ui.UIEvent
import com.marsn.minitalk.ui.feature.chat.conversation.ChatUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel(
    val contactUsecase: ContactUsecase
) : ViewModel() {

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState = _uiState.asStateFlow()

    fun loadContact(userId: Long) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) } // Opcional: mostrar um loading

            val contactFlow = contactUsecase.consultContact(userId)
            _uiState.update { currentState ->
                currentState.copy(
                    contact = contactFlow,
                    isLoading = false
                )
            }
        }
    }

    fun onEvent(event: ConversationEvent) {
        when (event) {

            is ConversationEvent.Chat -> {
                viewModelScope.launch {
                    _uiEvent.send(
                        UIEvent.NavigateToChat(
                            event.userId
                        )
                    )
                }
            }

            is ConversationEvent.Profile -> {
                viewModelScope.launch {
                    _uiEvent.send(
                        UIEvent.NavigateToProfile(
                            ChatRoutes.ProfileRoute,
                            event.userProfile
                        )
                    )

                }
            }

            is ConversationEvent.Home -> {
                viewModelScope.launch {
                    _uiEvent.send(UIEvent.NavigateBack)
                }
            }

            is ConversationEvent.Tab -> {
                viewModelScope.launch {
                    _uiEvent.send(UIEvent.ChangeTab(event.tab))
                }
            }

            is ConversationEvent.SearchText -> {
                viewModelScope.launch {
                    _uiEvent.send(UIEvent.ChangeSearch(event.text))
                }
            }

            else -> {}
        }
    }
}