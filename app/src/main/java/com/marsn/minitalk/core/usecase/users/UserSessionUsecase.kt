package com.marsn.minitalk.core.usecase.users

import com.marsn.minitalk.core.domain.UserSession
import com.marsn.minitalk.core.domain.conversation.Conversation
import com.marsn.minitalk.core.domain.contact.Contact
import com.marsn.minitalk.core.domain.contact.ContactResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface UserSessionUsecase {

    suspend fun consultUserSession(): UserSession?

}