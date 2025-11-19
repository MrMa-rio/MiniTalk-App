package com.marsn.minitalk.core.dataprovider.clients
import com.marsn.minitalk.core.domain.proto.ChatMessage
import com.marsn.minitalk.ui.feature.chat.conversation.MessageEventBus
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.url
import io.ktor.websocket.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.protobuf.ProtoBuf

class WebSocketChatClient(
    private val client: HttpClient
) {

    private var session: WebSocketSession? = null

    private val _incomingMessages = MutableSharedFlow<ChatMessage>()
    val incomingMessages = _incomingMessages.asSharedFlow()

    suspend fun connect(userId: Long) {
        session = client.webSocketSession {
            url("ws://172.16.232.241:8084/ws-chat?userId=$userId")

        }
        listenIncoming()
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun listenIncoming() {

        CoroutineScope(Dispatchers.Default).launch {
            try {
                for (frame in session!!.incoming) {
                    if (frame is Frame.Binary) {
                        val bytes = frame.data
                        val msg = ProtoBuf.decodeFromByteArray(ChatMessage.serializer(), bytes)
                        _incomingMessages.emit(msg) //TODO: PRECISA CRIAR UM LISTENER PRA RECEBER ESSA MENSAGEM E ATUALIZAR O BANCO ROOM
                    }
                }
            } catch (e: Exception) {
                println("Erro ao receber mensagem: $e")
            }
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    suspend fun send(chatMessage: ChatMessage) {
        val bytes = ProtoBuf.encodeToByteArray(ChatMessage.serializer(), chatMessage)
        session?.send(Frame.Binary(true, bytes))
    }

    suspend fun disconnect() {
        session?.close()
        session = null
    }
}
