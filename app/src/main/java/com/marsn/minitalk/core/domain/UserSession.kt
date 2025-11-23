package com.marsn.minitalk.core.domain

import kotlinx.serialization.Serializable

@Serializable
data class UserSession(
    val userId: Long,
    val name: String,
    val email: String,
    val token: String,
    val refreshToken: String,
    val photoUrl: String? = null
)
