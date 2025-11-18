package com.marsn.minitalk.navigation

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import com.marsn.minitalk.ui.feature.chat.conversation.ChatScreen
import com.marsn.minitalk.ui.feature.chat.contact.ContactsScreen
import com.marsn.minitalk.ui.feature.home.HomeScreen
import com.marsn.minitalk.ui.feature.login.LoginScreen
import com.marsn.minitalk.ui.feature.login.RegisterScreen
import com.marsn.minitalk.ui.feature.profile.ProfileScreen
import com.marsn.minitalk.ui.feature.splashCustomized.SplashAnimationScreen

@Composable
fun TalkNavDisplay() {

    var isNavigating by remember { mutableStateOf(false) }
    fun navigate(action: () -> Unit) {
        if (!isNavigating) {
            isNavigating = true
            action()
        }
    }
    DisposableEffect(Unit) {
        onDispose { isNavigating = false }
    }

    val backStack = rememberNavBackStack(AuthRoutes.SplashRoute)

    val navController = remember { NavController3(backStack) }

    CompositionLocalProvider(LocalNavController3 provides navController) {

        NavDisplay(
            backStack = backStack,
            contentAlignment = Alignment.Center,
            transitionSpec = {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(850)
                ) togetherWith slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(850)
                )
            },
            popTransitionSpec = {
                slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = tween(850)
                ) togetherWith slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(850)
                )
            },
            entryProvider = entryProvider {

                entry<AuthRoutes.SplashRoute> {
                    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    SplashAnimationScreen(
                        onNavigateTo = {
                            navigate {
                                navController.clearAndNavigate(AuthRoutes.LoginRoute)
                            }
                        }
                    )
                }

                entry<AuthRoutes.LoginRoute> {
                    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    LoginScreen(
                        onNavigateToRegister = {
                            navController.navigate(AuthRoutes.RegisterRoute)
                        },
                        onNavigateToHome = {
                            navController.clearAndNavigate(ChatRoutes.HomeRoute)
                        }
                    )
                }

                entry<AuthRoutes.RegisterRoute> {
                    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

                    RegisterScreen(
                        navigateTo = {
                            navController.clearAndNavigate(AuthRoutes.LoginRoute)
                        }
                    )
                }

                entry<ChatRoutes.HomeRoute> {
                    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    HomeScreen(navController)
                }

                entry<ChatRoutes.ProfileRoute> {
                    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

                    ProfileScreen(it.userProfile)

                }

                entry<ChatRoutes.ChatRoute> {
                    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    ChatScreen(it.userId)

                }

                entry<ChatRoutes.NewConversation> {
                    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    ContactsScreen()
                }
            }
        )
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
            activity?.requestedOrientation =
                originalOrientation ?: ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }
}
