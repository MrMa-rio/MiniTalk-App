package com.marsn.minitalk.navigation

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.marsn.minitalk.ui.feature.initial.HomeScreen
import com.marsn.minitalk.ui.feature.login.LoginScreen
import com.marsn.minitalk.ui.feature.login.RegisterScreen
import com.marsn.minitalk.ui.feature.splashCustomized.SplashAnimationScreen

@Composable
fun TalkNavHost() {
    val backStack = rememberNavBackStack(AuthRoutes.SplashRoute)

    var isNavigating by remember { mutableStateOf(false) }
    fun navigate(action: () -> Unit) {
        if (!isNavigating) {
            isNavigating = true
            action()
        }
    }
    DisposableEffect(backStack.lastOrNull()) {
        isNavigating = false
        onDispose { }
    }

    NavDisplay(
        backStack = backStack,
        contentAlignment = Alignment.Center,
        transitionSpec = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(850)
            ) togetherWith
                    slideOutHorizontally(
                        targetOffsetX = { -it },
                        animationSpec = tween(850)
                    )
        },
        popTransitionSpec = {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(850)
            ) togetherWith
                    slideOutHorizontally(
                        targetOffsetX = { it },
                        animationSpec = tween(850)
                    )
        },
        entryProvider = entryProvider {

            entry<AuthRoutes.SplashRoute> {
                LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                SplashAnimationScreen(onNavigateTo = {
                    navigate {
                        backStack.clear()
                        backStack.add(AuthRoutes.LoginRoute)
                    }
                })
            }

            entry<AuthRoutes.LoginRoute> {
                LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                LoginScreen(
                    onNavigateToRegister = { backStack.add(AuthRoutes.RegisterRoute) },
                    onNavigateToHome = {
                        navigate {
                            backStack.clear()
                            backStack.add(ChatRoutes.HomeRoute)
                        }
                    }
                )
            }

            entry<AuthRoutes.RegisterRoute> {
                LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                RegisterScreen(navigateTo = {
                    navigate {
                        backStack.clear()
                        backStack.add(AuthRoutes.LoginRoute) }
                })
            }

            entry<ChatRoutes.HomeRoute> {
                LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                HomeScreen()
            }
        }

    )


}

@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current
    val activity = context as? Activity
    DisposableEffect(Unit) {
        val originalOrientation = activity?.requestedOrientation
        activity?.requestedOrientation = orientation
        onDispose {
            activity?.requestedOrientation =
                originalOrientation ?: ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }
}
