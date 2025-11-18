package com.marsn.minitalk.ui.feature.chat.conversation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marsn.minitalk.core.usecase.users.ContactUsecase
import com.marsn.minitalk.navigation.ChatRoutes
import com.marsn.minitalk.ui.UIEvent
import com.marsn.minitalk.ui.feature.home.HomeUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MessagingViewModel(
    val contactUsecase: ContactUsecase
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
                    _uiEvent.send(UIEvent.ChangeSearch(event.message))
                }
            }
        }
    }
}