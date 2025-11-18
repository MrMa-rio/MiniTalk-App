package com.marsn.minitalk.ui.feature.chat.contact

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
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class ContactsViewModel(
    val contactUsecase: ContactUsecase,
    private val conversationUsecase: ConversationUsecase
) : ViewModel() {


    private val _uiEvent = Channel<UIEvent>()

    val uiEvent = _uiEvent.receiveAsFlow()
    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    val contacts = _searchText
        .combine(_contacts) { text, contactList ->
            if (text.isBlank()) {
                contactList
            } else {
                contactList.filter {
                    it.name.lowercase().contains(text.lowercase())
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _contacts.value
        )

    init {
        getAllContacts()
    }


    fun onEvent(event: ContactEvent) {
        when (event) {

            is ContactEvent.SearchText -> {
                _searchText.value = event.searchText
            }

            is ContactEvent.SelectContact -> {
                viewModelScope.launch {
                    val conversation = Conversation(
                        id = null,
                        conversationId = "",
                        userId = event.contact.userId,
                        createdAt = LocalDateTime.now(),
                        typeConversation = TypeConversation.PRIVATE
                    )
                    conversationUsecase.createConversation(
                        conversation
                    )
                    _uiEvent.send(UIEvent.NavigateToChat((conversation.conversationId)))
                }
            }

            is ContactEvent.NavigateBack -> {
                viewModelScope.launch {
                    _uiEvent.send(UIEvent.NavigateBack)
                }
            }
        }
    }

    private fun getAllContacts() {
        contactUsecase.consultAllContacts()
            .onEach { contactList ->
                _contacts.value = contactList
            }
            .launchIn(viewModelScope)
    }

}

