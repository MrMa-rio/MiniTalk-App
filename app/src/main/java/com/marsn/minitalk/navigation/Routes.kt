package com.marsn.minitalk.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class AuthRoutes() {
    @Serializable
    object SplashRoute : NavKey

    @Serializable
    object LoginRoute : NavKey

    @Serializable
    object RegisterRoute : NavKey

}

sealed class ChatRoutes() {

    @Serializable
    object HomeRoute : NavKey

    @Serializable
    object ChatRoute : NavKey

    @Serializable
    object ProfileRoute : NavKey
}


