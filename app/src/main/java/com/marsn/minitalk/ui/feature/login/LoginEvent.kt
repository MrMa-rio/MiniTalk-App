package com.marsn.minitalk.ui.feature.login

import com.marsn.minitalk.core.domain.UserProfile


sealed interface LoginEvent {



    data class Logged(val user: UserProfile) : LoginEvent
    object Login : LoginEvent

    object Register : LoginEvent
    object Registered: LoginEvent

}