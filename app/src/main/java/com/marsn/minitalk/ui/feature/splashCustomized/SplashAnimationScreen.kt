package com.marsn.minitalk.ui.feature.splashCustomized

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.marsn.minitalk.R
import com.marsn.minitalk.navigation.AuthRoutes
import com.marsn.minitalk.navigation.ChatRoutes
import com.marsn.minitalk.navigation.NavController3
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashAnimationScreen(navController: NavController3) {

    val viewModel = koinViewModel<SplashViewModel>()
    val uiEvent = remember { viewModel.uiEvent }

    LaunchedEffect(Unit) {
        uiEvent.collectLatest { event ->
            when (event) {
                is SplashEvent.Home-> {
                    navController.clearAndNavigate(ChatRoutes.HomeRoute)
                }
                is SplashEvent.Login -> {
                    navController.clearAndNavigate(AuthRoutes.LoginRoute)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF0FBFAD),
                        Color.White,
                        Color(0xFF0D9488)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .width(800.dp)
                .height(200.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher_3_foreground),
                contentDescription = "Logo MiniTalk",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
