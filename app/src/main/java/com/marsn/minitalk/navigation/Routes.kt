package com.marsn.minitalk.navigation

import androidx.navigation3.runtime.NavKey
import com.marsn.minitalk.core.domain.UserProfile
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
    object NewConversation : NavKey


    @Serializable
    class ProfileRoute(val userProfile: UserProfile) : NavKey

    @Serializable
    class ChatRoute(val conversationId: String) : NavKey

}


