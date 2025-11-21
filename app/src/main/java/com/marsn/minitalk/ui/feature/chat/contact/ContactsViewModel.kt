package com.marsn.minitalk.ui.feature.chat.contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marsn.minitalk.core.usecase.conversation.ConversationUsecase
import com.marsn.minitalk.core.usecase.users.ContactUsecase
import com.marsn.minitalk.core.usecase.users.UserSessionUsecase
import com.marsn.minitalk.ui.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactsViewModel(
    private val contactUsecase: ContactUsecase,
    private val conversationUsecase: ConversationUsecase,
    private val userSessionUsecase: UserSessionUsecase
) : ViewModel() {

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiState = MutableStateFlow(ContactUiState())
    val uiState = _uiState


    init {
        viewModelScope.launch {
            loadUserSession()
            uiState.value = uiState.value.copy(contacts = contactUsecase.consultAllContacts())
        }
    }

    suspend fun loadUserSession() {
        val userSession = userSessionUsecase.consultUserSession()
        _uiState.update { it.copy(userSession = userSession) }
    }

    fun onEvent(event: ContactEvent) {
        when (event) {

            is ContactEvent.SearchText -> {
                uiState.value = uiState.value.copy(searchText = event.searchText)
            }

            is ContactEvent.SelectContact -> {
                viewModelScope.launch {
                    val conversation = conversationUsecase.consultOrCreateUserConversation(
                        uiState.value.userSession?.userId ?: 0,
                        event.contact.userId
                    )
                    conversation.let {
                        _uiEvent.send(UIEvent.NavigateToChat(conversation.conversationId))
                    }
                }
            }

            is ContactEvent.NavigateBack -> {
                viewModelScope.launch {
                    _uiEvent.send(UIEvent.NavigateBack)
                }
            }

            is ContactEvent.OnRefresh -> {
                viewModelScope.launch {
                    uiState.value =
                        uiState.value.copy(contacts = contactUsecase.consultAllContactsAndSave())
                }
            }
        }
    }
}

