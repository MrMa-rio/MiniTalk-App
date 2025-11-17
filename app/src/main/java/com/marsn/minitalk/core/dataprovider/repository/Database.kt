package com.marsn.minitalk.core.dataprovider.repository

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationDao
import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationEntity
import com.marsn.minitalk.core.dataprovider.repository.converters.Converters
import com.marsn.minitalk.core.dataprovider.repository.message.messageChunk.MessageChunkDao
import com.marsn.minitalk.core.dataprovider.repository.message.messageChunk.MessageChunkEntity
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton


@Database(
    entities = [
        ConversationEntity::class,
        MessageChunkEntity::class
    ],
    version = 3
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