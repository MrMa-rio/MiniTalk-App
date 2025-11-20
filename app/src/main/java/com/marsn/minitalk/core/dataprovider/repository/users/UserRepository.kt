package com.marsn.minitalk.core.dataprovider.repository.users

import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getAllUsers(): Flow<List<UserEntity>>

    fun getActiveUsers(): Flow<List<UserEntity>>

    suspend fun getByLocalId(id: Long): UserEntity?

    suspend fun getByUserId(userId: Long): UserEntity?

    suspend fun syncUsers(users: List<UserEntity>)

    suspend fun saveUser(user: UserEntity)

    suspend fun updateAvatar(userId: Long, url: String, updatedAt: Long)
    suspend fun updateName(userId: Long, name: String, updatedAt: Long)
    suspend fun updateStatus(userId: Long, status: String?, updatedAt: Long)
    suspend fun deleteUser(userId: Long)
}
