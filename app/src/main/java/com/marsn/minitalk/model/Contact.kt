package com.marsn.minitalk.model

import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    val name: String,
    val phoneNumber: String,
    val email: String,
    val address: String,
    val profilePicture: String
) {
}