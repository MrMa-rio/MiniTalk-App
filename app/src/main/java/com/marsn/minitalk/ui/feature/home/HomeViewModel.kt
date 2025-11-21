package com.marsn.minitalk.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marsn.minitalk.core.usecase.conversation.ConversationUsecase
import com.marsn.minitalk.navigation.ChatRoutes
import com.marsn.minitalk.ui.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val conversationUsecase: ConversationUsecase,
) : ViewModel() {

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getAllConversations()
        }
    }

    fun onEvent(event: ConversationEvent) {
        when (event) {
            is ConversationEvent.SearchText -> {
                _uiState.value = _uiState.value.copy(searchText = event.text)
            }

            is ConversationEvent.Chat -> {
                viewModelScope.launch {
                    _uiEvent.send(
                        UIEvent.NavigateToChat(
                            event.conversationId
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
            else -> {}
        }
    }

    private suspend fun getAllConversations() {
        conversationUsecase.consultAllConversations(10)
            .onEach { conversationList ->
                _uiState.value = _uiState.value.copy(
                    conversations = conversationList
                )
            }
            .launchIn(viewModelScope)
    }
}
