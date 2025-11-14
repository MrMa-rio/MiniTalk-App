package com.marsn.minitalk.model

import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val name: String,
    val email: String,
    val phoneNumber: String,
)
