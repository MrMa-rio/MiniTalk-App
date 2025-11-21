package com.marsn.minitalk.ui.feature.login

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marsn.minitalk.core.dataprovider.clients.WebSocketManager
import com.marsn.minitalk.core.dataprovider.repository.userSession.UserSessionRepository
import com.marsn.minitalk.core.usecase.auth.AuthUsecase
import com.marsn.minitalk.core.usecase.users.ContactUsecase
import com.marsn.minitalk.navigation.AuthRoutes
import com.marsn.minitalk.navigation.ChatRoutes
import com.marsn.minitalk.ui.UIEvent
import com.marsn.minitalk.ui.feature.chat.conversation.MessagingViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

class LoginViewModel(
    private val socketManager: WebSocketManager,
    private val sessionRepository: UserSessionRepository
) : ViewModel() {



    init {
        viewModelScope.launch {
            sessionRepository.getUserSessionFlow().collect { session ->
                if (session != null) {
                    // já está logado → conectar socket e navegar
                    socketManager.connect(session.userId)
                    _uiEvent.send(UIEvent.NavigateTo(ChatRoutes.HomeRoute))
                }
            }
        }
    }

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiState = MutableStateFlow(LoginUiState())

    val uiState = _uiState.asStateFlow()


    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.Logged -> {
                viewModelScope.launch {
                    socketManager.connect(userId = event.user.userId)
                    sessionRepository.saveSession(event.user)
                    _uiEvent.send(UIEvent.NavigateTo(ChatRoutes.HomeRoute))
                }
            }

            is LoginEvent.Register -> {
                viewModelScope.launch {
                    _uiEvent.send(UIEvent.NavigateTo(AuthRoutes.RegisterRoute))
                }
            }

            is LoginEvent.Login -> {
                viewModelScope.launch {
                    _uiEvent.send(UIEvent.NavigateTo(AuthRoutes.LoginRoute))
                }
            }

            is LoginEvent.Registered -> {
                viewModelScope.launch {
                    _uiEvent.send(UIEvent.NavigateTo(ChatRoutes.HomeRoute))
                }
            }
        }
    }

}