package com.marsn.minitalk.core.di

import androidx.room.Room
import com.marsn.minitalk.core.dataprovider.repository.ChatDatabase
import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationRepository
import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationRepositoryImpl
import com.marsn.minitalk.core.dataprovider.repository.message.MessageRepository
import com.marsn.minitalk.core.dataprovider.repository.message.MessageRepositoryImpl
import com.marsn.minitalk.core.dataprovider.repository.message.messageChunk.MessageChunkRepository
import com.marsn.minitalk.core.dataprovider.repository.message.messageChunk.MessageChunkRepositoryImpl
import com.marsn.minitalk.core.usecase.conversation.ConversationUsecase
import com.marsn.minitalk.core.usecase.conversation.ConversationUsecaseImpl
import com.marsn.minitalk.core.usecase.message.SaveMessagesUseCase
import com.marsn.minitalk.core.usecase.message.SaveMessagesUseCaseImpl
import com.marsn.minitalk.ui.feature.chat.ChatViewModel
import com.marsn.minitalk.ui.feature.home.ConversationViewModel
import com.marsn.minitalk.ui.feature.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            ChatDatabase::class.java,
            "mini-talk"
        ).fallbackToDestructiveMigration(false)
            .build()
    }


    single { get<ChatDatabase>().conversationDao() }
    single { get<ChatDatabase>().messageChunkDao() }
    single { get<ChatDatabase>().messageDao() }
}

val repositoryModule = module {

    singleOf(::ConversationRepositoryImpl) {
        bind<ConversationRepository>()
    }
    singleOf(::MessageRepositoryImpl) {
        bind<MessageRepository>()
    }
    singleOf(::MessageChunkRepositoryImpl) {
        bind<MessageChunkRepository>()
    }
}

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::ConversationViewModel)
    viewModelOf(::ChatViewModel)
}

val usecaseModule = module {


    singleOf(::ConversationUsecaseImpl) {
        bind<ConversationUsecase>()
    }
    singleOf(::SaveMessagesUseCaseImpl) {
        bind<SaveMessagesUseCase>()
    }
}