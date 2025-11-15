package com.marsn.minitalk.ui.feature.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.marsn.minitalk.R
import com.marsn.minitalk.ui.feature.home.header.HomeContent
import com.marsn.minitalk.ui.mocks.messageContacts.messagesContactsMock
import com.marsn.minitalk.ui.mocks.messages.messagesMock


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {


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


    val gradient = remember {
        Brush.linearGradient(
            listOf(
                Color(0xFF0FBFAD),
                Color(0xFF1FBFAD),
                Color(0xFF2FBFAD),
                Color(0xFF3FBFAD)
            )
        )
    }




    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {/*TODO*/ },
                modifier = Modifier.offset(y = (-32).dp, x = (-16).dp),
                containerColor = Color(0xFF1FBFAD)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.msg),
                    contentDescription = "Add",
                    tint = Color.White,
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp)
                )
            }
        }) {
        Column(
            modifier = Modifier
                .padding(it)
                .background(gradient)
                .consumeWindowInsets(it)
        ) {
            HomeContent(messageContact = messagesContactsMock)
        }
    }
}