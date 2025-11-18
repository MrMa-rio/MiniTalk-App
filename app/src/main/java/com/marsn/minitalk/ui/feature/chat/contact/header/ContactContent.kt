package com.marsn.minitalk.ui.feature.chat.contact.header

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.marsn.minitalk.navigation.ChatRoutes
import com.marsn.minitalk.navigation.LocalNavController3
import com.marsn.minitalk.ui.UIEvent
import com.marsn.minitalk.ui.feature.chat.contact.ContactsViewModel
import com.marsn.minitalk.ui.feature.chat.contact.ListContact
import com.marsn.minitalk.ui.feature.chat.contact.TextInputSearch
import kotlinx.coroutines.flow.collectLatest


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContactContent(viewModel: ContactsViewModel) {


    val searchText by viewModel.searchText.collectAsState()
    val contacts = viewModel.contacts
    val uiEvent = remember { viewModel.uiEvent }
    val navController = LocalNavController3.current

    LaunchedEffect(Unit) {

        uiEvent.collectLatest { event ->
            when (event) {
                is UIEvent.NavigateToChat -> {
                    navController.navigate(ChatRoutes.ChatRoute(event.conversationId))
                }

                is UIEvent.NavigateBack -> {
                    navController.pop()
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
            TabsHeader(viewModel::onEvent)
            TextInputSearch (searchText = searchText, viewModel::onEvent)
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

                ListContact(contacts = contacts, viewModel::onEvent)
            }

        }
    }

}
