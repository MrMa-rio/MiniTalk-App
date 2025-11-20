package com.marsn.minitalk.core.dataprovider.repository.message

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: MessageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessages(messages: List<MessageEntity>)


    @Query("SELECT * FROM messages WHERE messageId = :id LIMIT 1")
    suspend fun getMessage(id: String): MessageEntity?

    @Delete
    suspend fun deleteMessage(message: MessageEntity)

    @Query(
        """
        SELECT * FROM messages
        WHERE conversationId = :conversationId
        ORDER BY timestamp DESC
        LIMIT :limit
    """
    )
    fun observeRecentMessages(
        conversationId: String,
        limit: Int
    ): Flow<List<MessageEntity>>


    @Query(
        """
        SELECT * FROM messages
        WHERE conversationId = :conversationId
        ORDER BY timestamp DESC
        LIMIT :limit
    """
    )
    fun getLatestMessages(
        conversationId: Long,
        limit: Int
    ): Flow<List<MessageEntity>>


    @Query(
        """
        SELECT * FROM messages
        WHERE conversationId = :conversationId AND timestamp < :timestamp
        ORDER BY timestamp DESC
        LIMIT :limit
    """
    )
    fun getOlderMessages(
        conversationId: Long,
        timestamp: Long,
        limit: Int
    ): Flow<List<MessageEntity>>
}
