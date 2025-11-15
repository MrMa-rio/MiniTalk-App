package com.marsn.minitalk.dataprovider.repository.conversation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ConversationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConversation(conv: ConversationEntity)

    @Query("SELECT * FROM conversations ORDER BY lastTimestamp DESC")
    fun getConversations(): Flow<List<ConversationEntity>>
}
