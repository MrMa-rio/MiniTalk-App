package com.marsn.minitalk.core.domain

import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val userId: Long,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val photoUrl: String,
)
