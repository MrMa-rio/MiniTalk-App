package com.marsn.minitalk.core.dataprovider.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationDao
import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationEntity
import com.marsn.minitalk.core.dataprovider.repository.converters.Converters
import com.marsn.minitalk.core.dataprovider.repository.message.MessageDao
import com.marsn.minitalk.core.dataprovider.repository.message.MessageEntity

@Database(
    entities = [
        ConversationEntity::class,
        MessageEntity::class
    ],
    version = 9
)
@TypeConverters(Converters::class)
abstract class ChatDatabase : RoomDatabase() {

    abstract fun conversationDao(): ConversationDao
    abstract fun messageDao(): MessageDao

}