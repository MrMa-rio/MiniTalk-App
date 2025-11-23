package com.marsn.minitalk.core.mapper

import com.marsn.minitalk.core.dataprovider.repository.users.UserEntity
import com.marsn.minitalk.core.domain.contact.Contact
import com.marsn.minitalk.core.domain.contact.ContactResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class ContactMapper() {
    companion object {



        fun toContact(response: ContactResponse): Contact {
            return Contact(
                userId = response.userId,
                name = response.name,
                phoneNumber = response.phone,
                email = response.email,
                avatarUrl = response.avatarUrl
            )
        }

        fun toUserEntity(contact: Contact): UserEntity {
            return UserEntity(
                userId = contact.userId,
                name = contact.name,
                phone = contact.phoneNumber,
                email = contact.email,
                avatarUrl = contact.avatarUrl,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
        }

        fun toContactList(contactResponse: Flow<List<ContactResponse>>): Flow<List<Contact>> {
            return contactResponse.map { response ->
                response.map { contact ->
                    Contact(
                        userId = contact.userId,
                        name = contact.name,
                        phoneNumber = contact.phone,
                        email = contact.email,
                        avatarUrl = contact.avatarUrl
                    )
                }
            }
        }
    }



}