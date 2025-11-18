package com.marsn.minitalk.ui.feature.chat.contact

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marsn.minitalk.core.domain.Conversation
import com.marsn.minitalk.core.domain.contact.ConversationMapper
import com.marsn.minitalk.core.shared.enums.TypeConversation
import com.marsn.minitalk.core.usecase.conversation.ConversationUsecase
import com.marsn.minitalk.core.usecase.users.ContactUsecase
import com.marsn.minitalk.ui.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class ContactsViewModel(
    contactUsecase: ContactUsecase,
    private val conversationUsecase: ConversationUsecase
) : ViewModel() {


    private val _uiEvent = Channel<UIEvent>()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()


    fun onEvent(event: ContactEvent) {
        when (event) {

            is ContactEvent.SearchText -> {
                _searchText.value = event.searchText
            }

            is ContactEvent.SelectContact -> {
                viewModelScope.launch {
                    conversationUsecase.createConversation(
                        Conversation(
                            id = null,
                            conversationId = "",
                            userId = event.contact.userId,
                            createdAt = LocalDateTime.now(),
                            typeConversation = TypeConversation.PRIVATE
                        )
                    )
                    _uiEvent.send(UIEvent.NavigateBack)
                }
            }

            is ContactEvent.NavigateBack -> {
                viewModelScope.launch {
                    _uiEvent.send(UIEvent.NavigateBack)
                }
            }
        }
    }


    var contacts = contactUsecase.consultAllContacts()
        private set

}
