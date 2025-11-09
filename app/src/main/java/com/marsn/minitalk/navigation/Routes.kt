package com.marsn.minitalk.navigation

import kotlinx.serialization.Serializable

@Serializable
object Route {}

@Serializable
data class InitialRoute(val id: Long) //TODO: Analisar porque utiliza esse cara