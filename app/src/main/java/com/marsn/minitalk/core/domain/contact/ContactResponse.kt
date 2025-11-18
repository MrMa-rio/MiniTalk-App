package com.marsn.minitalk.core.domain.contact

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

@Serializable
data class ContactResponse(
    val userId: Long,
    val name: String,
    val phoneNumber: String,
    val email: String,
    val address: String,
    val profilePicture: String
) {


}

fun toContact(response: ContactResponse): Contact {
    return Contact(
        userId = response.userId,
        name = response.name,
        phoneNumber = response.phoneNumber,
        email = response.email,
        address = response.address,
        profilePicture = response.profilePicture
    )
}

fun toContactList(contactResponse: Flow<List<ContactResponse>>): Flow<List<Contact>> {
    return contactResponse.map { response ->
        response.map { contact ->
            Contact(
                userId = contact.userId,
                name = contact.name,
                phoneNumber = contact.phoneNumber,
                email = contact.email,
                address = contact.address,
                profilePicture = contact.profilePicture
            )
        }
    }
}