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
interface ConversationParticipantsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertParticipant(participant: ConversationParticipantsEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertParticipants(list: List<ConversationParticipantsEntity>)

    @Delete
    suspend fun deleteParticipant(participant: ConversationParticipantsEntity)

    @Query("DELETE FROM conversation_participants WHERE conversationId = :conversationId AND participantId = :participantId")
    suspend fun removeUserFromConversation(conversationId: Long, participantId: Long)

    @Query("DELETE FROM conversation_participants WHERE conversationId = :conversationId")
    suspend fun removeAllParticipants(conversationId: Long)

    @Update
    suspend fun updateParticipant(participant: ConversationParticipantsEntity)

    @Query("SELECT * FROM conversation_participants WHERE conversationId = :conversationId")
    fun getParticipants(conversationId: Long): Flow<List<ConversationParticipantsEntity>>

    @Query("SELECT * FROM conversation_participants WHERE participantId = :participantId")
    fun getConversationsWhereUserIs(participantId: Long): Flow<List<ConversationParticipantsEntity>>

    @Query(
        """
        SELECT COUNT(*) FROM conversation_participants
        WHERE conversationId = :conversationId AND participantId = :participantId
    """
    )
    suspend fun isUserInConversation(conversationId: Long, participantId: Long): Boolean

    @Query(
        """
        SELECT role FROM conversation_participants
        WHERE conversationId = :conversationId AND participantId = :participantId
        LIMIT 1
    """
    )
    suspend fun getUserRole(conversationId: Long, participantId: Long): String?

}
