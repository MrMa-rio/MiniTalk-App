package com.marsn.minitalk.core.usecase.message

import com.marsn.minitalk.core.dataprovider.repository.message.MessageRepository
import com.marsn.minitalk.core.domain.Message

class SaveMessagesUseCaseImpl(
    private val messageRepository: MessageRepository
) : SaveMessagesUseCase {
    override suspend operator fun invoke(
        conversationId: String,
        messages: List<Message>
    ) {
        val entities = messages.map { it.toEntity() }

        // 1. Salva mensagens
        messageRepository.saveMessages(entities)

        // 2. Cria chunk automaticamente
    }
}
