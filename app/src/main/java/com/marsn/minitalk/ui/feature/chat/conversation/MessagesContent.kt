package com.marsn.minitalk.ui.feature.chat.conversation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marsn.minitalk.core.domain.MessageText
import com.marsn.minitalk.core.domain.proto.ChatMessage
import com.marsn.minitalk.ui.components.message.MessageBubbleFriend
import com.marsn.minitalk.ui.components.message.MessageBubbleOwn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MessagesList(
    messageList: List<MessageText>,
    userId: Long,
    loadMoreMessages: (Long) -> Unit,
    onEvent: () -> Unit
) {

    val listState = rememberLazyListState()

    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->

                if (index == 0 && !isLoading) {

                    val oldestMessage = messageList.lastOrNull()
                    if (oldestMessage != null) {
                        isLoading = true
                        loadMoreMessages(oldestMessage.timestamp)
                        isLoading = false
                    }
                }
            }
    }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        state = listState,
        reverseLayout = true
    ) {
        itemsIndexed(messageList) { _, message ->
            if (message.senderId == userId)
                MessageBubbleOwn(message)
            else
                MessageBubbleFriend(message)

            Spacer(Modifier.height(12.dp))
        }
    }
}

