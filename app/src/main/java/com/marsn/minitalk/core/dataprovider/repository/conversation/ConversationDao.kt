package com.marsn.minitalk.core.dataprovider.repository.conversation

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.marsn.minitalk.core.domain.conversation.ConversationItem
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
    suspend fun getConversationByConversationId(conversationId: String): ConversationEntity?

    @Query("SELECT * FROM conversations ORDER BY createdAt DESC")
    fun getAllConversations(): Flow<List<ConversationEntity>>

    @Query("SELECT * FROM conversations WHERE typeConversation = :type")
    fun getConversationsByType(type: TypeConversation): Flow<List<ConversationEntity>>


    @Query("""
    SELECT c.* FROM conversations AS c
    INNER JOIN conversation_participants AS cp
        ON cp.conversationId = c.conversationId
    WHERE cp.participantId = :participantId
      AND c.typeConversation = :type
""")
    fun getConversationsByParticipantId(
        participantId: Long,
        type: TypeConversation = TypeConversation.GROUP
    ): Flow<List<ConversationEntity>>

    @Query("""
    SELECT c.* FROM conversations AS c
    INNER JOIN conversation_participants AS cp
        ON cp.conversationId = c.conversationId
    WHERE cp.participantId = :participantId
      AND c.typeConversation = :type
    LIMIT 1
""")
    suspend fun getConversationByParticipantId(
        participantId: Long,
        type: TypeConversation = TypeConversation.PRIVATE
    ): ConversationEntity?


    @Query(
        """
    SELECT 
        c.conversationId AS conversationId,
        c.typeConversation AS typeConversation,
        u.name AS participantName,
        u.avatarUrl AS participantAvatar,
        m.content AS lastMessage,
        m.typeContent AS lastMessageType,
        m.timestamp AS lastMessageTimestamp,
        m.isRead AS isRead
    FROM conversations c

    INNER JOIN conversation_participants cp
        ON cp.conversationId = c.conversationId 
        AND cp.participantId != :currentUserId
    
    INNER JOIN users u
        ON u.userId = cp.participantId

    INNER JOIN messages m
        ON m.id = (
            SELECT id 
            FROM messages 
            WHERE conversationId = c.conversationId 
            ORDER BY timestamp DESC 
            LIMIT 1
        )

    ORDER BY m.timestamp DESC
"""
    )

    fun getConversationPreviews(
        currentUserId: Long
    ): Flow<List<ConversationItem>>
}
