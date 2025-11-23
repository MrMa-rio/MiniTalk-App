package com.marsn.minitalk.ui.feature.login

import com.marsn.minitalk.core.domain.UserProfile
import com.marsn.minitalk.core.domain.UserSession


sealed interface LoginEvent {



    data class Logged(val user: UserSession) : LoginEvent
    object Login : LoginEvent

    object Register : LoginEvent
    object Registered: LoginEvent

}