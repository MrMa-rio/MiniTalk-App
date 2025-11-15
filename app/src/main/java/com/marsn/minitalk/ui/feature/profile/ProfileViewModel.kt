package com.marsn.minitalk.ui.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marsn.minitalk.navigation.ChatRoutes
import com.marsn.minitalk.ui.UIEvent
import com.marsn.minitalk.ui.feature.home.ConversationEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onEvent(event: ConversationEvent) {
        when (event) {

            is ConversationEvent.Chat -> {
                viewModelScope.launch {
                    _uiEvent.send(UIEvent.NavigateToChat(ChatRoutes.ChatRoute, event.conversationId))
                }
            }
            is ConversationEvent.Profile -> {
                viewModelScope.launch {
                    _uiEvent.send(UIEvent.NavigateToProfile(ChatRoutes.ProfileRoute, event.userProfile))

                }
            }

            is ConversationEvent.Home -> {
                viewModelScope.launch {
                    _uiEvent.send(UIEvent.NavigateBack)
                }
            }
            else -> {}

        }
    }

}