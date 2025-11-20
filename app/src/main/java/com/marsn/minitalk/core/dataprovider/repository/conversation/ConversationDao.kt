package com.marsn.minitalk.core.dataprovider.repository.conversation

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.marsn.minitalk.core.shared.enums.TypeConversation
import kotlinx.coroutines.flow.Flow

@Dao
interface ConversationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertConversation(conv: ConversationEntity) : Long

    @Delete
    suspend fun deleteConversation(conversation: ConversationEntity)

    @Update
    suspend fun updateConversation(conversation: ConversationEntity)

    @Query("SELECT * FROM conversations WHERE conversationId = :conversationId LIMIT 1")
    suspend fun getConversationByConversationId(conversationId: Long): ConversationEntity?

    @Query("SELECT * FROM conversations ORDER BY createdAt DESC")
    fun getAllConversations(): Flow<List<ConversationEntity>>

    @Query("SELECT * FROM conversations WHERE typeConversation = :type")
    suspend fun getConversationsByType(type: TypeConversation): Flow<List<ConversationEntity>>


    @Query("""
        SELECT c.* FROM conversations AS c
        INNER JOIN conversation_participants AS cp
            ON cp.conversationId = c.conversationId
        WHERE cp.participantId = :participantId
        ORDER BY c.createdAt DESC
    """)
    suspend fun getConversationByDestinyId(participantId: Long): Flow<List<ConversationEntity>>

}
