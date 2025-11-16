package com.marsn.minitalk.core.domain

import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    val userId: Long,
    val name: String,
    val phoneNumber: String,
    val email: String,
    val address: String,
    val profilePicture: String
) {
}