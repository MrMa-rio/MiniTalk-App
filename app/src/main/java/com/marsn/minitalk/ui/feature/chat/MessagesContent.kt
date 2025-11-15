package com.marsn.minitalk.ui.feature.chat

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marsn.minitalk.model.MessageContact
import com.marsn.minitalk.model.MessageText
import com.marsn.minitalk.ui.components.message.MessageBubbleFriend
import com.marsn.minitalk.ui.components.message.MessageBubbleOwn

@Composable
fun MessagesList(
    messages: List<MessageText>,
    userId: Long,
    onEvent: (MessageEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        items(messages) { index ->
            if (userId == index.senderId)
                MessageBubbleOwn(index)
            else
                MessageBubbleFriend(index)

            Spacer(Modifier.height(12.dp))
        }
    }
}
