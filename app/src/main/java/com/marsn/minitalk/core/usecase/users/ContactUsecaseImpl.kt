package com.marsn.minitalk.core.usecase.users

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.marsn.minitalk.core.dataprovider.repository.users.UserEntity
import com.marsn.minitalk.core.dataprovider.repository.users.UserRepository
import com.marsn.minitalk.core.domain.contact.Contact
import com.marsn.minitalk.core.mapper.ContactMapper
import com.marsn.minitalk.ui.mocks.contacts.mocksContactResponseFlow
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class ContactUsecaseImpl(
    private val client: HttpClient,
    private val userRepository: UserRepository
) : ContactUsecase {
    override suspend fun consultContact(userId: Long): Contact? {

        val list = mocksContactResponseFlow.first()

        val contactResponse = list.firstOrNull { it.userId == userId }

        contactResponse?.let {
            return ContactMapper.toContact(contactResponse)
        }
        return null

    }

    override suspend fun consultAllContacts(): Flow<List<Contact>> {
        val contactsEntity = userRepository.getAllUsers()

        val contacts = ContactMapper.toContactList(contactsEntity)
        return contacts
    }

    override suspend fun consultAllContactsAndSave():  Flow<PagingData<Contact>> {
        val contacts = ContactMapper.toContactList(mocksContactResponseFlow)
        contacts.collect { it ->
            it.forEach {
                userRepository.saveUser(ContactMapper.toUserEntity(it))
            }
        }
        return getContactsFlow()
    }

    override suspend fun getContactsFlow(): Flow<PagingData<Contact>> {
        return userRepository.getAllUsersPaging().map { pagingData ->
            pagingData.map { contact ->
                Contact(
                    userId = contact.userId,
                    name = contact.name,
                    phoneNumber = contact.phone ?: "",
                    email = contact.email ?: "",
                    avatarUrl = contact.avatarUrl ?: ""
                )
            }

        }
    }

    fun ContactMapper.Companion.toContactList(contactResponse: Flow<List<UserEntity>>): Flow<List<Contact>> {
        return contactResponse.map { response ->
            response.map { contact ->
                Contact(
                    userId = contact.userId,
                    name = contact.name,
                    phoneNumber = contact.phone ?: "",
                    email = contact.email ?: "",
                    avatarUrl = contact.avatarUrl ?: ""
                )
            }
        }
    }
}
