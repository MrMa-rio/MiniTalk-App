package com.marsn.minitalk.ui.feature.home.header

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.marsn.minitalk.navigation.ChatRoutes
import com.marsn.minitalk.navigation.LocalNavController3
import com.marsn.minitalk.ui.UIEvent
import com.marsn.minitalk.ui.feature.home.ChatViewModel
import com.marsn.minitalk.ui.feature.home.ConversationViewModel
import com.marsn.minitalk.ui.feature.home.TextInputSearch
import com.marsn.minitalk.ui.feature.home.tabs.LayoutTab
import com.marsn.minitalk.ui.feature.home.tabs.ListChatTab
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeContent(viewModel: ConversationViewModel) {


    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val searchText by viewModel.searchText.collectAsState()

    val conversations by viewModel.conversations.collectAsState()

    val homeViewModel = koinViewModel<ChatViewModel>()
    val uiEvent = remember { homeViewModel.uiEvent }

    val navController = LocalNavController3.current
    LaunchedEffect(Unit) {

        uiEvent.collectLatest { event ->
            when (event) {
                is UIEvent.NavigateToChat-> {
                    navController.navigate(ChatRoutes.ChatRoute(event.userId))
                }

                is UIEvent.NavigateToProfile<*> -> {
                    navController.navigate(ChatRoutes.ProfileRoute(event.user))
                }
                is UIEvent.ChangeTab ->  selectedTabIndex = event.index

                is UIEvent.ChangeSearch -> {
                    conversations.filter {
                        "MOCK".lowercase().contains(searchText.lowercase())
                    }
                }

                else -> {}
            }
        }
    }


    Column {
        Column(
            modifier = Modifier
                .height(200.dp)
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TabsHeader(onEvent = homeViewModel::onEvent)
            LayoutTab(selectedTabIndex, onEvent = homeViewModel::onEvent)
            TextInputSearch(searchText, onEvent = viewModel::onEvent)
        }

        Column(
            modifier = Modifier
                .border(
                    0.dp,
                    Color.Transparent,
                    shape = RoundedCornerShape(42.dp, 42.dp, 0.dp, 0.dp)
                )
                .clip(RoundedCornerShape(42.dp, 42.dp, 0.dp, 0.dp))
                .background(Color.White)

        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
            ) {

                when (selectedTabIndex) {
                    0 -> ListChatTab(
                        messageContact = conversations,
                        onEvent = homeViewModel::onEvent)
                    1 -> ListChatTab(
                        messageContact = listOf(),
                        onEvent = homeViewModel::onEvent)
                }
            }

        }
    }

}
