package com.marsn.minitalk.dataprovider.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marsn.minitalk.dataprovider.repository.conversation.ConversationDao
import com.marsn.minitalk.dataprovider.repository.conversation.ConversationEntity
import com.marsn.minitalk.dataprovider.repository.message.messageChunk.MessageChunkEntity
import com.marsn.minitalk.dataprovider.repository.message.messageChunk.MessageChunkDao


@Database(
    entities = [
        ConversationEntity::class,
        MessageChunkEntity::class
    ],
    version = 1
)
abstract class ChatDatabase : RoomDatabase() {

    abstract fun conversationDao(): ConversationDao
    abstract fun messageChunkDao(): MessageChunkDao

}

object TodoDatabaseProvider {
    @Volatile
    private var INSTANCE: ChatDatabase? = null

    fun provider(context: Context): ChatDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                ChatDatabase::class.java,
                "todo-app"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}