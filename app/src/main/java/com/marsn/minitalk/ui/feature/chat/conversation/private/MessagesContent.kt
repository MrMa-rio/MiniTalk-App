package com.marsn.minitalk.ui.feature.chat.conversation.private

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marsn.minitalk.core.domain.MessageText
import com.marsn.minitalk.ui.components.message.MessageBubbleFriend
import com.marsn.minitalk.ui.components.message.MessageBubbleOwn

@Composable
fun MessagesList(
    messageList: List<MessageText>,
    currentUserId: Long,
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
            if (message.senderId == currentUserId)
                MessageBubbleOwn(message)
            else
                MessageBubbleFriend(message)

            Spacer(Modifier.height(12.dp))
        }
    }
}

