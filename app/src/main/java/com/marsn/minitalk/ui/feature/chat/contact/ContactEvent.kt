package com.marsn.minitalk.ui.feature.chat.contact

import com.marsn.minitalk.core.domain.contact.Contact


sealed interface ContactEvent {

    object NavigateBack : ContactEvent

    data class SearchText(val searchText: String): ContactEvent
    data class SelectContact(val contact: Contact): ContactEvent

}
