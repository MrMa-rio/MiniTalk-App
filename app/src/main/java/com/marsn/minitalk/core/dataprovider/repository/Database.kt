package com.marsn.minitalk.core.dataprovider.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationDao
import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationEntity
import com.marsn.minitalk.core.dataprovider.repository.converters.Converters
import com.marsn.minitalk.core.dataprovider.repository.message.MessageDao
import com.marsn.minitalk.core.dataprovider.repository.message.MessageEntity
import com.marsn.minitalk.core.dataprovider.repository.message.messageChunk.MessageChunkDao
import com.marsn.minitalk.core.dataprovider.repository.message.messageChunk.MessageChunkEntity


@Database(
    entities = [
        ConversationEntity::class,
        MessageChunkEntity::class,
        MessageEntity::class
    ],
    version = 8
)
@TypeConverters(Converters::class)
abstract class ChatDatabase : RoomDatabase() {

    abstract fun conversationDao(): ConversationDao
    abstract fun messageChunkDao(): MessageChunkDao
    abstract fun messageDao(): MessageDao

}