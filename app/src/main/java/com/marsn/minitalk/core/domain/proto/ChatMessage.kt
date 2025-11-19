package com.marsn.minitalk.core.domain.proto

import com.marsn.minitalk.core.domain.MessageText
import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage(
    val messageId: String,
    val conversationId: String,
    val senderId: Long,
    val content: String,
    val timestamp: String,
    val destinyId: Long
) {

}
fun ChatMessage.toMessageText(): MessageText {
    return MessageText(
        1,
        this.senderId,
        this.content,
        "PRIVATE",
        null,
        100000,
        "ENTREGUE",
        isDeleted = false,
        isEdited = false,
    )
}
