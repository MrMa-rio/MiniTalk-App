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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.marsn.minitalk.core.domain.Conversation
import com.marsn.minitalk.core.domain.MessageContact
import com.marsn.minitalk.navigation.ChatRoutes
import com.marsn.minitalk.navigation.LocalNavController3
import com.marsn.minitalk.ui.UIEvent
import com.marsn.minitalk.ui.components.inputsText.TextInputSearch
import com.marsn.minitalk.ui.feature.home.HomeViewModel
import com.marsn.minitalk.ui.feature.home.tabs.LayoutTab
import com.marsn.minitalk.ui.feature.home.tabs.ListChatTab
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeContent(messageContactList: List<Conversation>) {


    var selectedTabIndex by remember { mutableIntStateOf(0) }

    var searchText by remember { mutableStateOf("") }

    var messageContacts by remember { mutableStateOf(messageContactList) }


    val homeViewModel = viewModel<HomeViewModel> { HomeViewModel() }
    val uiEvent = remember { homeViewModel.uiEvent }

    val navController = LocalNavController3.current
    LaunchedEffect(Unit) {

        uiEvent.collectLatest { event ->
            when (event) {
                is UIEvent.NavigateToChat<*> -> {
                    navController.navigate(ChatRoutes.ChatRoute(event.conversationId))
                }

                is UIEvent.NavigateToProfile<*> -> {
                    navController.navigate(ChatRoutes.ProfileRoute(event.user))
                }
                is UIEvent.ChangeTab ->  selectedTabIndex = event.index

                is UIEvent.ChangeSearch -> {
                    searchText = event.searchText
                    messageContacts = messageContactList.filter {
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
            TextInputSearch(searchText, onEvent = homeViewModel::onEvent)
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
                        messageContact = messageContacts,
                        onEvent = homeViewModel::onEvent)
                    1 -> ListChatTab(
                        messageContact = listOf(),
                        onEvent = homeViewModel::onEvent)
                }
            }

        }
    }

}
