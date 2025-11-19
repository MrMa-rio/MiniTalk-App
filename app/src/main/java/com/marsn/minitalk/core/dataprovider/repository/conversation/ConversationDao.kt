package com.marsn.minitalk.core.dataprovider.repository.conversation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ConversationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConversation(conv: ConversationEntity) : Long

    @Query("SELECT * FROM conversations ORDER BY createdAt DESC")
    fun getAllConversations(): Flow<List<ConversationEntity>>

    @Query("SELECT * FROM conversations WHERE conversationId = :conversationId")
    suspend fun getConversationById(conversationId: Long): ConversationEntity?

    @Query("SELECT * FROM conversations WHERE destinyId = :destinyId LIMIT 1")
    fun getConversationByDestinyId(destinyId: List<Long>): ConversationEntity?
}
