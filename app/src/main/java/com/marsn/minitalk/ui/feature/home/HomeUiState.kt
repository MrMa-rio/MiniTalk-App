package com.marsn.minitalk.ui.feature.home

import com.marsn.minitalk.core.domain.contact.Contact

data class HomeUiState(
    val userId: Long = 0,
    val inputText: String = "",
    val contact: Contact? = null,
    val isLoading: Boolean = true
) {



}