package com.marsn.minitalk.ui.feature.login


sealed interface LoginEvent {



    data class Logged(val username: String) : LoginEvent


}