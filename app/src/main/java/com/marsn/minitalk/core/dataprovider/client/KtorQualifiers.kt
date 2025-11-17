package com.marsn.minitalk.core.dataprovider.client

import org.koin.core.qualifier.named

// Crie um novo arquivo, por exemplo, DiQualifiers.kt
// Qualificadores para os diferentes HttpClients
object KtorQualifiers {
    val USERS_API = named("UsersApi")
    val PAYMENTS_API = named("PaymentsApi")
}