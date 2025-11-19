package com.marsn.minitalk.core.di

import androidx.room.Room
import com.marsn.minitalk.core.dataprovider.client.KtorQualifiers
import com.marsn.minitalk.core.dataprovider.client.PathsAPI
import com.marsn.minitalk.core.dataprovider.clients.WebSocketChatClient
import com.marsn.minitalk.core.dataprovider.clients.WebSocketManager
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
import com.marsn.minitalk.core.usecase.users.ContactUsecase
import com.marsn.minitalk.core.usecase.users.ContactUsecaseImpl
import com.marsn.minitalk.ui.feature.chat.contact.ContactsViewModel
import com.marsn.minitalk.ui.feature.chat.conversation.MessagingViewModel
import com.marsn.minitalk.ui.feature.home.ConversationViewModel
import com.marsn.minitalk.ui.feature.home.ChatViewModel
import com.marsn.minitalk.ui.feature.login.LoginViewModel
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import io.ktor.client.engine.android.*
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.request.header
import io.ktor.http.cio.CIOHeaders
import io.ktor.serialization.kotlinx.json.json

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
    viewModelOf(::ChatViewModel)
    viewModelOf(::ConversationViewModel)
    viewModelOf(::ContactsViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::MessagingViewModel)

}

val usecaseModule = module {

    singleOf(::ConversationUsecaseImpl) {
        bind<ConversationUsecase>()
    }
    singleOf(::SaveMessagesUseCaseImpl) {
        bind<SaveMessagesUseCase>()
    }

    singleOf(::ContactUsecaseImpl) {
        bind<ContactUsecase>()
    }
}

val networkModule = module {
    single(KtorQualifiers.USERS_API) {
        HttpClient(Android) {
            defaultRequest {
                url(PathsAPI.MINI_TALK_API.name)
            }
            install(ContentNegotiation) {
                json()
            }
            engine {
                connectTimeout = 30_000
                socketTimeout = 30_000
            }
        }
    }

    single(KtorQualifiers.WEBSOCKETS) {
        HttpClient(engineFactory = CIO ) {
            install(WebSockets)
        }
    }

    single() {
        WebSocketChatClient(get(KtorQualifiers.WEBSOCKETS))
    }

    single() { WebSocketManager(get()) }

    single(KtorQualifiers.PAYMENTS_API) {
        HttpClient(Android) {
            defaultRequest {
                url(PathsAPI.MINI_TALK_API_TEST.name)
                header("Authorization", "Bearer SEU_TOKEN_DE_PAGAMENTOS")
            }

            install(ContentNegotiation) {
                json()
            }
            engine {
                connectTimeout = 90_000
                socketTimeout = 90_000
            }
        }
    }
    single {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json()
            }
        }
    }
}