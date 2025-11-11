package com.marsn.minitalk.navigation

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marsn.minitalk.ui.feature.initial.InitialScreen
import com.marsn.minitalk.ui.feature.login.LoginScreen
import com.marsn.minitalk.ui.feature.login.RegisterScreen
import com.marsn.minitalk.ui.feature.splashCustomized.SplashAnimationScreen

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ToDoNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SplashRoute
    ) {

        // Tela Splash
        composable<SplashRoute>(

        ) {
            LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            SplashAnimationScreen {
                navController.navigate(LoginRoute) {
                    popUpTo(SplashRoute) { inclusive = true }
                    launchSingleTop = true
                }
            }
        }

        // Tela Login
        composable<LoginRoute>(
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it }, // começa fora da tela à direita
                    animationSpec = tween(600),
                )
            },
            sizeTransform = {
                SizeTransform(
                    clip = false
                )
            }
        ) {
            LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            LoginScreen(navController)
        }

        // Tela Registro
        composable<RegisterRoute>(
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it + 5 }, // começa fora da tela à direita
                    animationSpec = tween(600)
                ) // combina deslizamento + fade
            },
            sizeTransform = {
                SizeTransform(
                    clip = false
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it - 8 }, // sai fora da tela à esquerda
                    animationSpec = tween(900)
                )
            }

        ) {
            LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            RegisterScreen(
                navController
            )
        }

        // Tela Inicial
        composable<InitialRoute>(

        ) {
            InitialScreen(navController)
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
            activity?.requestedOrientation =
                originalOrientation ?: ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }
}