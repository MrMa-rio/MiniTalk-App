package com.marsn.minitalk.core.dataprovider.repository.conversation

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.marsn.minitalk.core.dataprovider.repository.users.UserEntity
import com.marsn.minitalk.core.domain.Conversation
import com.marsn.minitalk.core.shared.enums.TypeConversation
import com.marsn.minitalk.core.shared.enums.TypeParticipant
import java.time.LocalDateTime


@Entity(
    tableName = "conversation_participants",
    foreignKeys = [
        ForeignKey(
            entity = ConversationEntity::class,
            parentColumns = ["id"],
            childColumns = ["conversationId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("conversationId"),
        Index("userId")
    ]
)
data class ConversationParticipantsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val conversationId: Long,
    val participantId: Long,
    val role: TypeParticipant = TypeParticipant.MEMBER,
    val joinedAt: Long,
    val typeConversation: TypeConversation

) {}
