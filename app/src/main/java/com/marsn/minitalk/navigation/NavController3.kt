package com.marsn.minitalk.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

class NavController3(private val backStack: NavBackStack<NavKey>) {
    fun navigate(route: NavKey) {
        backStack.add(route)
    }

    fun pop() {
        backStack.removeLastOrNull()
    }

    fun clearAndNavigate(route: NavKey) {
        backStack.clear()
        backStack.add(route)
    }
}
