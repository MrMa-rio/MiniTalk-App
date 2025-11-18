package com.marsn.minitalk.ui.feature.home

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.marsn.minitalk.R
import com.marsn.minitalk.navigation.ChatRoutes
import com.marsn.minitalk.navigation.NavController3
import com.marsn.minitalk.ui.feature.home.header.HomeContent
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navigate: NavController3) {


    val viewModel = koinViewModel<ConversationViewModel>()

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
                onClick = {
                    navigate.navigate(ChatRoutes.NewConversation) },
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
            HomeContent(viewModel)
        }
    }
}
