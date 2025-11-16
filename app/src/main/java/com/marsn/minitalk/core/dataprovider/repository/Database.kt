package com.marsn.minitalk.core.dataprovider.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationDao
import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationEntity
import com.marsn.minitalk.core.dataprovider.repository.converters.Converters
import com.marsn.minitalk.core.dataprovider.repository.message.messageChunk.MessageChunkEntity
import com.marsn.minitalk.core.dataprovider.repository.message.messageChunk.MessageChunkDao


@Database(
    entities = [
        ConversationEntity::class,
        MessageChunkEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ChatDatabase : RoomDatabase() {

    abstract fun conversationDao(): ConversationDao
    abstract fun messageChunkDao(): MessageChunkDao

}

object ChatDatabaseProvider {
    @Volatile
    private var INSTANCE: ChatDatabase? = null

    fun provider(context: Context): ChatDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                ChatDatabase::class.java,
                "mini-talk"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}