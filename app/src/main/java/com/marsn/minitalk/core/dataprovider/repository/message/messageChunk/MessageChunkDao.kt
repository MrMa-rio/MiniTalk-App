package com.marsn.minitalk.core.dataprovider.repository.message.messageChunk

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface MessageChunkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChunk(message: MessageChunkEntity)

    @Query("SELECT * FROM message_chunks WHERE conversationId = :conversationId ORDER BY endTimestamp DESC LIMIT :limit")
    suspend fun getChunks(conversationId: Long, limit: Int): List<MessageChunkEntity>

    @Query("""
        SELECT * FROM message_chunks 
        WHERE conversationId = :conversationId 
        AND endTimestamp < :cursor 
        ORDER BY endTimestamp DESC 
        LIMIT :limit
    """)
    suspend fun getChunksFromCursor(
        conversationId: Long,
        cursor: Long,
        limit: Int
    ): List<MessageChunkEntity>

    @Query("DELETE FROM message_chunks WHERE conversationId = :conversationId")
    suspend fun deleteConversationChunks(conversationId: Long)
}
