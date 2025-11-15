package com.marsn.minitalk.ui.feature.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.marsn.minitalk.model.MessageText
import com.marsn.minitalk.navigation.ChatRoutes
import com.marsn.minitalk.navigation.LocalNavController3
import com.marsn.minitalk.ui.UIEvent
import com.marsn.minitalk.ui.components.message.ChatInput
import com.marsn.minitalk.ui.components.screenTheme.BackgroundThemeChat
import com.marsn.minitalk.ui.feature.home.HomeViewModel
import com.marsn.minitalk.ui.mocks.messages.messagesMock
import kotlinx.coroutines.flow.collectLatest


@Composable
fun ChatScreen(conversationId: Long) {


    val homeViewModel = viewModel<HomeViewModel> { HomeViewModel() }
    val uiEvent = remember { homeViewModel.uiEvent }

    val context = LocalContext.current.applicationContext
//    val database = TodoDatabaseProvider.provider(context)
//    val repository = TodoRepositoryImpl(
//        todoDao = database.todoDao
//    )
//    val viewModel = viewModel<ChatViewModel> {
//        ChatViewModel(
//            repository = repository
//        )
//    }

    val navController = LocalNavController3.current
    LaunchedEffect(Unit) {
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

                ChatHeader(homeViewModel::onEvent)
                Box(modifier = Modifier.weight(1f)) {
                    MessagesList(messages = messagesMock, 101) {}
                }
                ChatInput()
            }
        }
    }
}


