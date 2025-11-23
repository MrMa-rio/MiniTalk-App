package com.marsn.minitalk.core.dataprovider.repository.conversation

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.marsn.minitalk.core.dataprovider.repository.users.UserEntity
import com.marsn.minitalk.core.domain.conversation.Conversation
import com.marsn.minitalk.core.shared.enums.TypeConversation
import com.marsn.minitalk.core.shared.enums.TypeParticipant
import java.time.LocalDateTime


@Entity(
    tableName = "conversation_participants",
    foreignKeys = [
        ForeignKey(
            entity = ConversationEntity::class,
            parentColumns = ["conversationId"],
            childColumns = ["conversationId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["userId"],
            childColumns = ["participantId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("conversationId"),
        Index("participantId")
    ]
)
data class ConversationParticipantsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val conversationId: String,
    val participantId: Long,
    val role: TypeParticipant = TypeParticipant.MEMBER,
    val joinedAt: Long,
    val typeConversation: TypeConversation

) {}
