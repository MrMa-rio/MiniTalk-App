package com.marsn.minitalk.ui.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marsn.minitalk.navigation.AuthRoutes
import com.marsn.minitalk.navigation.ChatRoutes
import com.marsn.minitalk.ui.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onEvent(event: LoginEvent) {
        when (event) {

            is LoginEvent.Logged -> {
                viewModelScope.launch {
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