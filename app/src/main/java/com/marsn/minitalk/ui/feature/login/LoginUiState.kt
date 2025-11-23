package com.marsn.minitalk.ui.feature.login

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val onUsernameChanged: (String) -> Unit = {},
    val onPasswordChanged: (String) -> Unit = {}
) {
}