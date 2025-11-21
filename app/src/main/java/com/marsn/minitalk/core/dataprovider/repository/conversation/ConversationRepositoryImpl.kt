package com.marsn.minitalk.core.dataprovider.repository.conversation

import android.util.Log
import androidx.room.withTransaction
import com.marsn.minitalk.core.dataprovider.repository.ChatDatabase
import com.marsn.minitalk.core.domain.conversation.Conversation
import com.marsn.minitalk.core.domain.conversation.ConversationItem
import com.marsn.minitalk.core.shared.enums.TypeConversation
import com.marsn.minitalk.core.shared.enums.TypeParticipant
import kotlinx.coroutines.flow.Flow
import java.nio.ByteBuffer
import java.security.MessageDigest
import java.util.UUID
import kotlin.math.max
import kotlin.math.min

const val algorithm = "SHA-256"

class ConversationRepositoryImpl(
    private val conversationDao: ConversationDao,
    private val conversationParticipantsDao: ConversationParticipantsDao,
    private val database: ChatDatabase

) : ConversationRepository {
    override suspend fun getConversationsPreview(currentUserId: Long): Flow<List<ConversationItem>> {
        return conversationDao.getConversationPreviews(currentUserId)
    }


    companion object {
        private const val TAG = "ConversationRepo"
        fun deterministicConversationId(senderId: Long, destinyId: Long): String {
            val a = min(senderId, destinyId)
            val b = max(senderId, destinyId)
            val seed = "$a:$b"

            val md = MessageDigest.getInstance(algorithm)
            val digest = md.digest(seed.toByteArray(Charsets.UTF_8))
            val buffer = ByteBuffer.wrap(digest, 0, 8)
            return buffer.long.toString()
        }

        fun randomConversationId(): String {
            return UUID.randomUUID().mostSignificantBits.toString()
        }
    }

    override suspend fun getConversationByConversationId(conversationId: String): ConversationEntity? {
        return conversationDao.getConversationByConversationId(conversationId)
    }

    override suspend fun getConversationsForParticipantId(participantId: Long): Flow<List<ConversationEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getConversationsGroupForParticipantId(participantId: Long): Flow<List<ConversationEntity>> {
        return conversationDao.getConversationsByParticipantId(participantId, TypeConversation.GROUP)
    }

    override suspend fun getParticipantsByConversationId(conversationId: String): Flow<List<ConversationParticipantsEntity>> {
        return conversationParticipantsDao.getParticipants(conversationId)
    }

    override suspend fun createPrivateConversation(
        senderId: Long,
        destinyId: Long
    ): ConversationEntity {
        val conversationId = deterministicConversationId(senderId, destinyId)
        conversationDao.getConversationByConversationId(conversationId)?.let { existing ->
            return existing
        }

        val now = System.currentTimeMillis()
        val conversation = ConversationEntity(
            conversationId = conversationId,
            typeConversation = TypeConversation.PRIVATE,
            createdAt = now
        )

        database.withTransaction {
            val inserted = conversationDao.insertConversation(conversation)
            if (inserted == -1L) {
                val existing = conversationDao.getConversationByConversationId(conversationId)
                if (existing != null) return@withTransaction
            }

            val p1 = ConversationParticipantsEntity(
                conversationId = conversationId,
                participantId = senderId,
                role = TypeParticipant.MEMBER,
                joinedAt = now,
                typeConversation = TypeConversation.PRIVATE
            )
            val p2 = ConversationParticipantsEntity(
                conversationId = conversationId,
                participantId = destinyId,
                role = TypeParticipant.MEMBER,
                joinedAt = now,
                typeConversation = TypeConversation.PRIVATE
            )

            conversationParticipantsDao.insertParticipant(p1)
            conversationParticipantsDao.insertParticipant(p2)
        }

        // garante leitura final (pode ocorrer que o runInTransaction tenha retornado antes)
        return conversationDao.getConversationByConversationId(conversationId)
            ?: throw IllegalStateException("Falha ao criar/obter conversa privada")
    }

    override suspend fun createGroupConversation(
        creatorId: Long,
        groupName: String,
        participants: List<Long>
    ): ConversationEntity {
        val conversationId = randomConversationId()
        val now = System.currentTimeMillis()
        val conversation = ConversationEntity(
            conversationId = conversationId,
            typeConversation = TypeConversation.GROUP,
            createdAt = now
        )

        database.withTransaction {
            val inserted = conversationDao.insertConversation(conversation)
            if (inserted == -1L) {
                Log.w(
                    TAG,
                    "createGroupConversation: conflito ao inserir conversa de grupo (id=${conversationId})"
                )
            }

            val all = (participants + creatorId).distinct()
            val participantEntities = all.map { participantId ->
                ConversationParticipantsEntity(
                    conversationId = conversationId,
                    participantId = participantId,
                    role = if (participantId == creatorId) TypeParticipant.ADMIN else TypeParticipant.MEMBER,
                    joinedAt = now,
                    typeConversation = TypeConversation.GROUP
                )
            }
            conversationParticipantsDao.insertParticipants(participantEntities)
        }

        return conversationDao.getConversationByConversationId(conversationId)
            ?: throw IllegalStateException("Falha ao criar grupo")
    }


    override suspend fun deleteConversation(conversation: ConversationEntity) {
        database.withTransaction {
            conversationParticipantsDao.removeAllParticipants(conversation.conversationId)
            conversationDao.deleteConversation(conversation)
        }
    }

    override suspend fun addParticipant(
        conversationId: String,
        participantId: Long,
        role: TypeParticipant
    ) {
        val now = System.currentTimeMillis()
        val participant = ConversationParticipantsEntity(
            conversationId = conversationId,
            participantId = participantId,
            role = role,
            joinedAt = now,
            typeConversation = TypeConversation.GROUP
        )
        conversationParticipantsDao.insertParticipant(participant)
    }

    override suspend fun removeParticipant(conversationId: String, participantId: Long) {
        conversationParticipantsDao.removeUserFromConversation(conversationId, participantId)
    }

    override suspend fun getConversationByParticipantId(participantId: Long): Conversation? {
        val conversation =
            conversationDao.getConversationByParticipantId(participantId, TypeConversation.PRIVATE)
        return conversation?.let {
            Conversation(
                conversationId = it.conversationId,
                typeConversation = it.typeConversation,
                createdAt = it.createdAt
            )
        }
    }
}