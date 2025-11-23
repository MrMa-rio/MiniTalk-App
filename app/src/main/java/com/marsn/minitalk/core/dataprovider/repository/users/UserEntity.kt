package com.marsn.minitalk.core.dataprovider.repository.users


import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.marsn.minitalk.core.domain.contact.Contact
import com.marsn.minitalk.core.domain.contact.ContactResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Entity(
    tableName = "users",
    indices = [Index(value = ["userId"], unique = true)]
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val userId: Long,
    val name: String,
    val email: String? = null,
    val phone: String? = null,
    val avatarUrl: String? = null,
    val status: String? = null,
    val isActive: Boolean = true,

    val createdAt: Long,
    val updatedAt: Long
)


