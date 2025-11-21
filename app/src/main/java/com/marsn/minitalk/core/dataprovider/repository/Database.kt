package com.marsn.minitalk.core.dataprovider.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationDao
import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationEntity
import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationParticipantsDao
import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationParticipantsEntity
import com.marsn.minitalk.core.dataprovider.repository.converters.Converters
import com.marsn.minitalk.core.dataprovider.repository.message.MessageDao
import com.marsn.minitalk.core.dataprovider.repository.message.MessageEntity
import com.marsn.minitalk.core.dataprovider.repository.users.UserDao
import com.marsn.minitalk.core.dataprovider.repository.users.UserEntity

@Database(
    entities = [
        ConversationEntity::class,
        ConversationParticipantsEntity::class,
        UserEntity::class,
        MessageEntity::class,

    ],
    version = 14
)
@TypeConverters(Converters::class)
abstract class ChatDatabase : RoomDatabase() {

    abstract fun conversationDao(): ConversationDao
    abstract fun messageDao(): MessageDao
    abstract fun userDao(): UserDao
    abstract fun conversationParticipantsDao(): ConversationParticipantsDao
}
