package com.marsn.minitalk.core.usecase.message

import com.marsn.minitalk.core.domain.Message

interface SaveMessagesUseCase {
    suspend operator fun invoke(
        conversationId: Long,
        messages: List<Message>
    )
}
