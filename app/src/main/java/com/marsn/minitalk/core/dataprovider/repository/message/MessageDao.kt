package com.marsn.minitalk.core.dataprovider.repository.message

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {


    @Transaction
    suspend fun upsert(message: MessageEntity) {
        val existing = getByMessageId(message.messageId)
        if (existing == null) {
            insert(message)
        } else {
            update(message.copy(id = existing.id))
        }
    }

    @Transaction
    suspend fun upsertAll(messages: List<MessageEntity>) {
        messages.forEach { upsert(it) }
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(message: MessageEntity): Long

    @Update
    suspend fun update(message: MessageEntity)


    @Query("SELECT * FROM messages WHERE messageId = :messageId LIMIT 1")
    suspend fun getByMessageId(messageId: Long): MessageEntity?


    @Query(
        """
        SELECT * FROM messages
        WHERE conversationId = :conversationId
        ORDER BY timestamp DESC
    """
    )
    fun getMessages(conversationId: Long): Flow<List<MessageEntity>>

    // Paginação manual (carregar mensagens antigas)
    @Query(
        """
        SELECT * FROM messages
        WHERE conversationId = :conversationId
        AND timestamp < :beforeTimestamp
        ORDER BY timestamp DESC
        LIMIT :limit
    """
    )
    fun getOlderMessages(
        conversationId: Long,
        beforeTimestamp: Long,
        limit: Int
    ): Flow<List<MessageEntity>>

    @Query(
        """
        SELECT * FROM messages
        WHERE conversationId = :conversationId
        ORDER BY timestamp DESC
        LIMIT 1
    """
    )
    suspend fun getLastMessage(conversationId: Long): MessageEntity?


    @Query("UPDATE messages SET isSent = :value WHERE messageId = :messageId")
    suspend fun updateIsSent(messageId: Long, value: Boolean)

    @Query("UPDATE messages SET isDelivered = :value WHERE messageId = :messageId")
    suspend fun updateIsDelivered(messageId: Long, value: Boolean)

    @Query("UPDATE messages SET isRead = :value WHERE messageId = :messageId")
    suspend fun updateIsRead(messageId: Long, value: Boolean)

    @Query("UPDATE messages SET isDeleted = :value WHERE messageId = :messageId")
    suspend fun updateIsDeleted(messageId: Long, value: Boolean)

    @Query("UPDATE messages SET isEdited = :value WHERE messageId = :messageId")
    suspend fun updateIsEdited(messageId: Long, value: Boolean)

    @Query("DELETE FROM messages WHERE conversationId = :conversationId")
    suspend fun deleteByConversation(conversationId: Long)

}
