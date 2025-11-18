package com.marsn.minitalk.core.usecase.users

import com.marsn.minitalk.core.domain.contact.Contact
import com.marsn.minitalk.core.domain.contact.toContact
import com.marsn.minitalk.core.domain.contact.toContactList
import com.marsn.minitalk.ui.mocks.contacts.mocksContactResponseFlow
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class ContactUsecaseImpl(
    private val client: HttpClient
) : ContactUsecase {
    override suspend fun consultContact(userId: Long): Contact? {

        val list = mocksContactResponseFlow.first()

        val contactResponse = list.firstOrNull { it.userId == userId }

        contactResponse?.let {
            return toContact(contactResponse)
        }
        return null
    }


    override fun consultAllContacts(): Flow<List<Contact>> {

        return toContactList(mocksContactResponseFlow)
    }
}