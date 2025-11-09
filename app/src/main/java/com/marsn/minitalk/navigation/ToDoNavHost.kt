package com.marsn.minitalk.navigation

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marsn.minitalk.ui.feature.initial.InitialScreen
import com.marsn.minitalk.ui.feature.login.LoginScreen

@Composable
fun ToDoNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route
    ) {

        composable<Route> {
            LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            LoginScreen( navController
            )
        }

        composable<InitialRoute> { backStackEntry ->

            InitialScreen()

        }

    }


}
@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current
    val activity = context as? Activity
    DisposableEffect(Unit) {
        val originalOrientation = activity?.requestedOrientation
        activity?.requestedOrientation = orientation
        onDispose {
            // restaura quando sair da tela
            activity?.requestedOrientation = originalOrientation ?: ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }
}