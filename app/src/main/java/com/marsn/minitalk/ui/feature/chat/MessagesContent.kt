package com.marsn.minitalk.ui.feature.chat

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marsn.minitalk.ui.components.message.MessageBubbleFriend
import com.marsn.minitalk.ui.components.message.MessageBubbleOwn

@Composable
fun MessagesList() {
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        reverseLayout = true
    ) {
        items(200) { index ->
            if (index % 2 == 0)
                MessageBubbleOwn("Mensagem do usuário $index")
            else
                MessageBubbleFriend("Mensagem recebida $index")

            Spacer(Modifier.height(12.dp))
        }
    }
}
