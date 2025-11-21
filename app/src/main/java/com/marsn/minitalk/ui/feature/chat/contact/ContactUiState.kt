package com.marsn.minitalk.ui.feature.chat.contact

import com.marsn.minitalk.core.domain.UserSession
import com.marsn.minitalk.core.domain.contact.Contact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ContactUiState(
    val contacts: Flow<List<Contact>> = emptyFlow(),
    val userSession: UserSession? = null,
    val searchText: String = "",
    val isLoading: Boolean = true,

) {

}
