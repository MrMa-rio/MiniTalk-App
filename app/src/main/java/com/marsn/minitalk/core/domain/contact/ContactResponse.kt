package com.marsn.minitalk.core.domain.contact

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

@Serializable
data class ContactResponse(
    val userId: Long,
    val name: String,
    val phone: String,
    val email: String,
    val avatarUrl: String
) {

}

