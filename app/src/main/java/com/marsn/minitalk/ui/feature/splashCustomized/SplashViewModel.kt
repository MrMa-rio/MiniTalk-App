package com.marsn.minitalk.ui.feature.splashCustomized

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marsn.minitalk.core.dataprovider.clients.WebSocketManager
import com.marsn.minitalk.core.dataprovider.repository.userSession.UserSessionRepository
import com.marsn.minitalk.navigation.AuthRoutes
import com.marsn.minitalk.navigation.ChatRoutes
import com.marsn.minitalk.ui.UIEvent
import com.marsn.minitalk.ui.feature.login.LoginEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val socketManager: WebSocketManager,
    private val sessionRepository: UserSessionRepository
) : ViewModel() {

    private val _uiEvent = Channel<SplashEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            delay(2000) // <- aqui é o lugar correto do delay
            sessionRepository.getUserSessionFlow().collect { session ->
                if (session != null) {
                    _uiEvent.send(SplashEvent.Home)
                } else {
                    _uiEvent.send(SplashEvent.Login)
                }
            }
        }
    }



}