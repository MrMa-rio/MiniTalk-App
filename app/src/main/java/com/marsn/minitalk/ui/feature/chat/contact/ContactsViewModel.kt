package com.marsn.minitalk.ui.feature.chat.contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marsn.minitalk.core.domain.conversation.Conversation
import com.marsn.minitalk.core.shared.enums.TypeConversation
import com.marsn.minitalk.core.usecase.users.ContactUsecase
import com.marsn.minitalk.ui.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class ContactsViewModel(
    private val contactUsecase: ContactUsecase
) : ViewModel() {

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiState = MutableStateFlow(ContactUiState())
    val uiState = _uiState


    init {
        viewModelScope.launch {
            uiState.value = uiState.value.copy(contacts = contactUsecase.consultAllContacts())
        }
    }

    fun onEvent(event: ContactEvent) {
        when (event) {

            is ContactEvent.SearchText -> {
                uiState.value = uiState.value.copy(searchText = event.searchText)
            }

            is ContactEvent.SelectContact -> {
                viewModelScope.launch {
                    _uiEvent.send(UIEvent.NavigateToChat((event.contact.userId)))
                }
            }

            is ContactEvent.NavigateBack -> {
                viewModelScope.launch {
                    _uiEvent.send(UIEvent.NavigateBack)
                }
            }

            is ContactEvent.OnRefresh -> {
                viewModelScope.launch {
                    uiState.value = uiState.value.copy(contacts = contactUsecase.consultAllContactsAndSave())
                }
            }
        }
    }
}

