package com.marsn.minitalk.ui.feature.splashCustomized

import com.marsn.minitalk.core.domain.UserProfile
import com.marsn.minitalk.core.domain.UserSession


sealed interface SplashEvent {


    object Login : SplashEvent
    object Home: SplashEvent

}