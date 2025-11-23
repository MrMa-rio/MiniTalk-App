package com.marsn.minitalk.core.dataprovider.clients
import com.marsn.minitalk.core.dataprovider.middleware.MessageMiddleware
import com.marsn.minitalk.core.domain.proto.ChatMessage
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.url
import io.ktor.websocket.*
import kotlinx.coroutines.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.protobuf.ProtoBuf

class WebSocketChatClient(
    private val client: HttpClient,
    private val middleware: MessageMiddleware
) {

    private var session: WebSocketSession? = null


    suspend fun connect(currentUserId: Long) {
        session = client.webSocketSession {
            url("ws://192.168.3.92:8084/ws-chat?userId=$currentUserId")
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
                        middleware.onMessage(msg)
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
