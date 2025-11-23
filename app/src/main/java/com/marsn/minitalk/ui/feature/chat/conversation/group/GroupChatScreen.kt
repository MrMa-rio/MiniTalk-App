package com.marsn.minitalk.ui.feature.chat.conversation.group

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.marsn.minitalk.navigation.ChatRoutes
import com.marsn.minitalk.navigation.LocalNavController3
import com.marsn.minitalk.ui.UIEvent
import com.marsn.minitalk.ui.components.message.ChatInput
import com.marsn.minitalk.ui.components.screenTheme.BackgroundThemeChat
import com.marsn.minitalk.ui.feature.chat.conversation.ChatHeader
import com.marsn.minitalk.ui.feature.chat.conversation.private.MessagesList
import com.marsn.minitalk.ui.feature.chat.conversation.private.PrivateMessagingViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun GroupChatScreen(conversationId: String) {

    val privateMessagingViewModel = koinViewModel<PrivateMessagingViewModel>()
    val uiEvent = remember { privateMessagingViewModel.uiEvent }

    val state = privateMessagingViewModel.uiState.collectAsState()
    val messages = state.value.messages
    val conversation = state.value.conversation


    val navController = LocalNavController3.current
    LaunchedEffect(Unit) {
//        privateMessagingViewModel.loadConversationAndContact(conversationId)
        uiEvent.collectLatest { event ->
            when (event) {
                is UIEvent.NavigateBack -> {
                    navController.clearAndNavigate(ChatRoutes.HomeRoute)
                }

                else -> {}
            }
        }
    }

    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            BackgroundThemeChat()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.3f))
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
            ) {

                ChatHeader(state.value.contentHeader, privateMessagingViewModel::onEvent)
                Box(modifier = Modifier.weight(1f)) {
                    MessagesList(
                        messageList = messages,
                        currentUserId = state.value.userSession?.userId ?: 0,
                        {
                            privateMessagingViewModel.loadOlderMessages(
                                conversation?.conversationId ?: "",
                                it
                            )
                        },
                        {}
                    )
                }
                ChatInput(state.value.inputText, privateMessagingViewModel::onEvent)
            }
        }
    }
}


