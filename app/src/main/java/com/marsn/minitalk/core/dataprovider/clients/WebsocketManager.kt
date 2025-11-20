package com.marsn.minitalk.core.dataprovider.clients

import com.marsn.minitalk.core.domain.proto.ChatMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class WebSocketManager(
    private val chatClient: WebSocketChatClient
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private var isConnected = false


    fun connect(userId: Long) {
        if (isConnected) return
        isConnected = true
        scope.launch {
            try {
                chatClient.connect(userId)
                println("CONECTADO COM SUCESSO!!")
            } catch (e: Exception) {
                isConnected = false
                println("Erro conectando WebSocket: $e")
            }
        }
    }

    fun send(chatMessage: ChatMessage) {
        scope.launch {
            chatClient.send(chatMessage)
            println("MENSAGEM ENVIADA!!")
        }
    }

    fun disconnect() {
        scope.launch {
            chatClient.disconnect()
            isConnected = false
        }
    }
}
