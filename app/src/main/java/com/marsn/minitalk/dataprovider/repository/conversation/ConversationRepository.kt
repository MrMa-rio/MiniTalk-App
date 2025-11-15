package com.marsn.minitalk.dataprovider.repository.conversation

import com.marsn.minitalk.domain.Conversation

interface ConversationRepository {

    suspend fun createConversation(conversation: Conversation): Result<Unit>

    suspend fun getConversationById(conversationId: String): Result<Conversation>

    suspend fun getUserConversations(userId: String): Result<List<Conversation>>

    suspend fun updateConversation(conversation: Conversation): Result<Unit>

    /**
     * Sincroniza as conversas entre o Room (offline) e o MongoDB (online).
     * Retorna true se ocorreu alguma atualização local.
     */
    suspend fun syncConversations(userId: String): Result<Boolean>
}
