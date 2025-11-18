package com.marsn.minitalk.ui.feature.chat.contact

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marsn.minitalk.core.domain.Conversation
import com.marsn.minitalk.core.domain.contact.Contact
import com.marsn.minitalk.core.shared.enums.TypeConversation
import com.marsn.minitalk.core.usecase.conversation.ConversationUsecase
import com.marsn.minitalk.core.usecase.users.ContactUsecase
import com.marsn.minitalk.navigation.ChatRoutes
import com.marsn.minitalk.ui.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class ContactsViewModel(
    contactUsecase: ContactUsecase,
    private val conversationUsecase: ConversationUsecase
) : ViewModel() {

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiState = MutableStateFlow(ContactUiState())
    val uiState = _uiState

    init {
        _uiState.value = _uiState.value.copy(contacts = contactUsecase.consultAllContacts())
    }

    fun onEvent(event: ContactEvent) {
        when (event) {

            is ContactEvent.SearchText -> {
                uiState.value = uiState.value.copy(searchText = event.searchText)
                uiState.value.onChangedSearchText(event.searchText)
            }

            is ContactEvent.SelectContact -> {
                viewModelScope.launch {
                    val conversation = Conversation(
                        id = null,
                        userId = event.contact.userId,
                        createdAt = LocalDateTime.now(),
                        typeConversation = TypeConversation.PRIVATE
                    )
                    _uiEvent.send(UIEvent.NavigateToChat((conversation.userId)))
                }
            }

            is ContactEvent.NavigateBack -> {
                viewModelScope.launch {
                    _uiEvent.send(UIEvent.NavigateBack)
                }
            }
        }
    }
}

