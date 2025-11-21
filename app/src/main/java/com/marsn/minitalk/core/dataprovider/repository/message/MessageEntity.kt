package com.marsn.minitalk.core.dataprovider.repository.message

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationEntity
import com.marsn.minitalk.core.dataprovider.repository.users.UserEntity
import com.marsn.minitalk.core.shared.enums.TypeContent

@Entity(
    tableName = "messages",
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
            childColumns = ["senderId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("conversationId"),
        Index("senderId")
    ]
)
data class MessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val messageId: Long,
    val conversationId: Long,
    val senderId: Long,
    val content: String,
    val typeContent: TypeContent,
    val timestamp: Long,
    val isSent: Boolean,
    val isDelivered: Boolean,
    val isRead: Boolean,
    val isDeleted: Boolean,
    val isEdited: Boolean
) {
}
