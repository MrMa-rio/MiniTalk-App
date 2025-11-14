package com.marsn.minitalk.ui

import com.marsn.minitalk.model.UserProfile

sealed interface UIEvent {


    data class ShowSnackbar(val message: String) : UIEvent

    data object NavigateBack : UIEvent

    data class NavigateTo<T : Any>(val route: T) : UIEvent

    data class NavigateToChat<T : Any>(val route: T, val conversationId: Long) : UIEvent

    data class NavigateToProfile<T : Any>(val route: T, val user: UserProfile) : UIEvent


}
