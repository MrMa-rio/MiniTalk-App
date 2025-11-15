package com.marsn.minitalk.navigation

import androidx.navigation3.runtime.NavKey
import com.marsn.minitalk.model.Contact
import com.marsn.minitalk.model.UserProfile
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
    class ChatRoute(val conversationId: Long, val contact: Contact) : NavKey

    @Serializable
    class ProfileRoute(val userProfile: UserProfile) : NavKey

    @Serializable
    object NewConversationRoute : NavKey
}


