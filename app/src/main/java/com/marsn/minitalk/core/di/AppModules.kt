package com.marsn.minitalk.core.di

import androidx.room.Room
import com.marsn.minitalk.core.dataprovider.client.KtorQualifiers
import com.marsn.minitalk.core.dataprovider.client.PathsAPI
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
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
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

//val networkModule = module {

val networkModule = module {// Cliente para a API de Usuários
    single(KtorQualifiers.USERS_API) {
        HttpClient(Android) {
            // Plugin para adicionar configurações a TODAS as chamadas deste cliente
            defaultRequest {
                url(PathsAPI.MINI_TALK_API.name) // URL base
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

    // Cliente para a API de Pagamentos
    single(KtorQualifiers.PAYMENTS_API) {
        HttpClient(Android) {
            defaultRequest {
                url(PathsAPI.MINI_TALK_API_TEST.name) // URL base diferente
                header("Authorization", "Bearer SEU_TOKEN_DE_PAGAMENTOS") // Ex: Header de autenticação
            }

            install(ContentNegotiation) {
                json()
            }
            // Pode ter configurações de engine diferentes se necessário
            engine {
                connectTimeout = 90_000 // Ex: Timeout maior para pagamentos
                socketTimeout = 90_000
            }
        }
    }

    // Você ainda pode ter um cliente genérico se precisar
    single {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json()
            }
        }
    }
}