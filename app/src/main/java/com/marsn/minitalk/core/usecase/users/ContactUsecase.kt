package com.marsn.minitalk.core.usecase.users

import com.marsn.minitalk.core.domain.Conversation
import com.marsn.minitalk.core.domain.contact.Contact
import com.marsn.minitalk.core.domain.contact.ContactResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ContactUsecase {

    suspend fun consultContact(userId: Long): Contact?

    fun consultAllContacts(): Flow<List<Contact>>


}