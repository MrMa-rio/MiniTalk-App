package com.marsn.minitalk.ui.feature.splashCustomized

import androidx.compose.runtime.Composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import com.marsn.minitalk.R

@Composable
fun SplashAnimationScreen(
    onNavigateNext: () -> Unit // callback para ir pra próxima tela
) {
    // Controla visibilidade e animação
    var visible by remember { mutableStateOf(false) }

    // Animação de opacidade
    val alphaAnim = animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 1500)
    )

    // Efeito de tempo (simula carregamento)
    LaunchedEffect(Unit) {
        visible = true
        delay(2000) // tempo total de exibição
        onNavigateNext()
    }

    // Layout
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

@Preview
@Composable
fun SplashAnimationScreenPreview() {
    SplashAnimationScreen(onNavigateNext = {})
}
