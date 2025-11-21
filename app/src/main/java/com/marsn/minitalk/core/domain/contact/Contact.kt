package com.marsn.minitalk.core.domain.contact

import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    val userId: Long,
    val name: String,
    val phoneNumber: String,
    val email: String,
    val avatarUrl: String
) {



}