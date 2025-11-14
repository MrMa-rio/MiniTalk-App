package com.marsn.minitalk.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marsn.minitalk.navigation.ChatRoutes
import com.marsn.minitalk.ui.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

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
        }
    }

}