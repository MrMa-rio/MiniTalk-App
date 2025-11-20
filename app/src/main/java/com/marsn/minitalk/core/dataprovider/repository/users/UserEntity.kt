package com.marsn.minitalk.core.dataprovider.repository.users


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String? = null,
    val name: String,
    val email: String? = null,
    val phone: String? = null,
    val avatarUrl: String? = null,
    val status: String? = null,
    val isActive: Boolean = true,

    val createdAt: Long,
    val updatedAt: Long
)
