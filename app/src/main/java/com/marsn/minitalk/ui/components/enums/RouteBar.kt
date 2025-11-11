package com.marsn.minitalk.ui.components.enums

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.twotone.ChatBubble
import androidx.compose.ui.graphics.vector.ImageVector


enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    CHAT("Chat", Icons.Default.ChatBubble),
    PROFILE("Profile", Icons.Default.AccountBox),
}