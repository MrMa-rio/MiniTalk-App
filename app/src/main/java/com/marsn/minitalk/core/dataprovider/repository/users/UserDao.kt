package com.marsn.minitalk.core.dataprovider.repository.users

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {


    @Transaction
    suspend fun upsert(user: UserEntity) {
        val existing = getByUserId(user.userId)
        if (existing == null) {
            insert(user)
        } else {
            update(user.copy(userId = existing.userId))
        }
    }

    @Transaction
    suspend fun upsertAll(users: List<UserEntity>) {
        users.forEach { upsert(it) }
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: UserEntity): Long

    @Update
    suspend fun update(user: UserEntity)


    @Query("SELECT * FROM users WHERE userId = :userId LIMIT 1")
    suspend fun getByLocalId(userId: Long): UserEntity?

    @Query("SELECT * FROM users WHERE userId = :userId LIMIT 1")
    suspend fun getByUserId(userId: Long?): UserEntity?

    @Query("SELECT * FROM users ORDER BY name ASC")
    fun getAll(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE isActive = 1 ORDER BY name ASC")
    fun getActiveUsers(): Flow<List<UserEntity>>

    @Query("UPDATE users SET avatarUrl = :url, updatedAt = :updatedAt WHERE userId = :userId")
    suspend fun updateAvatar(userId: Long, url: String?, updatedAt: Long)

    @Query("UPDATE users SET name = :name, updatedAt = :updatedAt WHERE userId = :userId")
    suspend fun updateName(userId: Long, name: String, updatedAt: Long)

    @Query("UPDATE users SET status = :status, updatedAt = :updatedAt WHERE userId = :userId")
    suspend fun updateStatus(userId: Long, status: String?, updatedAt: Long)

    @Query("DELETE FROM users WHERE userId = :userId")
    suspend fun deleteByUserId(userId: Long)

    @Query("SELECT COUNT(*) FROM users")
    suspend fun countUsers(): Int

    @Query("SELECT * FROM users ORDER BY name ASC")
    fun getContactsPaging(): PagingSource<Int, UserEntity>

}
