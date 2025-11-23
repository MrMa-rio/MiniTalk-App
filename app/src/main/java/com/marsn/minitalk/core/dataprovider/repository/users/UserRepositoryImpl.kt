package com.marsn.minitalk.core.dataprovider.repository.users

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {

    override fun getAllUsers(): Flow<List<UserEntity>> {
        return userDao.getAll()
    }

    override fun getActiveUsers(): Flow<List<UserEntity>> {
        return userDao.getActiveUsers()
    }

    override suspend fun getByLocalId(id: Long): UserEntity? {
        return userDao.getByLocalId(id)
    }

    override suspend fun getByUserId(userId: Long): UserEntity? {
        return userDao.getByUserId(userId)
    }

    override suspend fun syncUsers(users: List<UserEntity>) {
        userDao.upsertAll(users)
    }

    override suspend fun saveUser(user: UserEntity) {
        userDao.upsert(user)
    }

    override suspend fun updateAvatar(userId: Long, url: String, updatedAt: Long) {
        userDao.updateAvatar(userId, url, updatedAt)
    }

    override suspend fun updateName(userId: Long, name: String, updatedAt: Long) {
        userDao.updateName(userId, name, updatedAt)
    }

    override suspend fun updateStatus(userId: Long, status: String?, updatedAt: Long) {
        userDao.updateStatus(userId, status, updatedAt)
    }

    override suspend fun deleteUser(userId: Long) {
        userDao.deleteByUserId(userId)
    }

    override suspend fun getAllUsersPaging(): Flow<PagingData<UserEntity>> {
        val total = userDao.countUsers()

        val pageSize = when {
            total < 100 -> 20
            total < 500 -> 30
            total < 2_000 -> 40
            else -> 60
        }

        val prefetch = pageSize / 2

        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                prefetchDistance = prefetch,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { userDao.getContactsPaging() }
        ).flow
    }
}
