package com.marsn.minitalk.core.dataprovider.clients

import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class WebSocketClient(
    private val client: HttpClient,
    private val url: String
) {

    private var session: WebSocketSession? = null

    private val _incomingMessages = MutableSharedFlow<String>()
    val incomingMessages = _incomingMessages.asSharedFlow()

    private var reconnectJob: Job? = null

    suspend fun connect() {
        reconnectJob?.cancel()

        reconnectJob = CoroutineScope(Dispatchers.Default).launch {
            while (isActive) {
                try {
                    client.webSocket(urlString = url) {
                        session = this

                        // Listen for messages
                        for (frame in incoming) {
                            when (frame) {
                                is Frame.Text -> _incomingMessages.emit(frame.readText())
                                is Frame.Close -> {
                                    println("WebSocket closed")
                                    break
                                }
                                else -> {}
                            }
                        }
                    }
                } catch (e: Exception) {
                    println("WebSocket error: $e")
                }

                delay(2000) // try to reconnect
                println("Reconnecting WebSocket...")
            }
        }
    }

    suspend fun send(message: String) {
        session?.send(Frame.Text(message))
    }

    suspend fun disconnect() {
        reconnectJob?.cancel()
        session?.close()
        session = null
    }
}
