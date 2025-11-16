package com.marsn.minitalk.core.dataprovider.repository.message

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: MessageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessages(messages: List<MessageEntity>)

    @Query("SELECT * FROM messages WHERE conversationId = :conversationId ORDER BY timestamp DESC LIMIT :limit OFFSET :offset")
    suspend fun getMessagesPaged(
        conversationId: String,
        limit: Int,
        offset: Int
    ): List<MessageEntity>

    @Query("SELECT COUNT(*) FROM messages WHERE conversationId = :conversationId")
    suspend fun countMessages(conversationId: String): Int

    @Query("SELECT * FROM messages WHERE messageId = :id LIMIT 1")
    suspend fun getMessage(id: String): MessageEntity?

    @Delete
    suspend fun deleteMessage(message: MessageEntity)
}
