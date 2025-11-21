package com.marsn.minitalk.core.di

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.room.Room
import com.marsn.minitalk.core.dataprovider.client.KtorQualifiers
import com.marsn.minitalk.core.dataprovider.client.PathsAPI
import com.marsn.minitalk.core.dataprovider.clients.WebSocketChatClient
import com.marsn.minitalk.core.dataprovider.clients.WebSocketManager
import com.marsn.minitalk.core.dataprovider.middleware.MessageMiddleware
import com.marsn.minitalk.core.dataprovider.repository.ChatDatabase
import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationRepository
import com.marsn.minitalk.core.dataprovider.repository.conversation.ConversationRepositoryImpl
import com.marsn.minitalk.core.dataprovider.repository.message.MessageRepository
import com.marsn.minitalk.core.dataprovider.repository.message.MessageRepositoryImpl
import com.marsn.minitalk.core.dataprovider.repository.userSession.UserSessionRepository
import com.marsn.minitalk.core.dataprovider.repository.userSession.UserSessionRepositoryImpl
import com.marsn.minitalk.core.dataprovider.repository.users.UserRepository
import com.marsn.minitalk.core.dataprovider.repository.users.UserRepositoryImpl
import com.marsn.minitalk.core.domain.UserSession
import com.marsn.minitalk.core.usecase.auth.AuthUsecase
import com.marsn.minitalk.core.usecase.auth.AuthUsecaseImpl
import com.marsn.minitalk.core.usecase.conversation.ConversationUsecase
import com.marsn.minitalk.core.usecase.conversation.ConversationUsecaseImpl
import com.marsn.minitalk.core.usecase.message.MessagesUseCase
import com.marsn.minitalk.core.usecase.message.MessagesUseCaseImpl
import com.marsn.minitalk.core.usecase.users.ContactUsecase
import com.marsn.minitalk.core.usecase.users.ContactUsecaseImpl
import com.marsn.minitalk.infraestructure.UserSessionSerializer
import com.marsn.minitalk.ui.feature.chat.contact.ContactsViewModel
import com.marsn.minitalk.ui.feature.chat.conversation.MessagingViewModel
import com.marsn.minitalk.ui.feature.home.HomeViewModel
import com.marsn.minitalk.ui.feature.login.LoginViewModel
import com.marsn.minitalk.ui.feature.splashCustomized.SplashViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
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
    single { get<ChatDatabase>().messageDao() }
    single { get<ChatDatabase>().userDao() }
    single { get<ChatDatabase>().conversationParticipantsDao() }
}


val dataStoreModule = module {

    single<DataStore<UserSession?>> {
        val context = androidContext()
        DataStoreFactory.create(
            serializer = UserSessionSerializer,
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = {
                context.filesDir
                    .resolve("datastore")
                    .apply { mkdirs() }
                    .resolve("user_session.json")
            }
        )
    }

    singleOf(::UserSessionRepositoryImpl) {
        bind<UserSessionRepository>()
    }
}

val repositoryModule = module {

    singleOf(::ConversationRepositoryImpl) {
        bind<ConversationRepository>()
    }
    singleOf(::MessageRepositoryImpl) {
        bind<MessageRepository>()
    }

    singleOf(::UserRepositoryImpl) {
        bind<UserRepository>()
    }

}

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::ContactsViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::MessagingViewModel)
    viewModelOf(::SplashViewModel)

}

val usecaseModule = module {

    singleOf(::ConversationUsecaseImpl) {
        bind<ConversationUsecase>()
    }

    singleOf(::ContactUsecaseImpl) {
        bind<ContactUsecase>()
    }

    singleOf(::AuthUsecaseImpl) {
        bind<AuthUsecase>()
    }

    singleOf(::MessagesUseCaseImpl) {
        bind<MessagesUseCase>()
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

    single {
        MessageMiddleware(get())
    }

    single() {
        WebSocketChatClient(get(KtorQualifiers.WEBSOCKETS), get())
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