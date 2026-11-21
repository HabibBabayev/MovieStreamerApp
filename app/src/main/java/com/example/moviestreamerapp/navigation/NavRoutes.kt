package com.example.moviestreamerapp.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoutes(val route: String) {
    data object Splash: NavRoutes("splash_screen")
    data object Intro: NavRoutes("intro_screen")
    data object AuthEntry: NavRoutes("auth_entry_screen")
    data object Login: NavRoutes("login_screen")
    data object SignUp: NavRoutes("signUp_screen")
    data object ForgotPassword: NavRoutes("forgot_password")
    data object NewPassword: NavRoutes("new_password")
    data object Profile: NavRoutes("profile_screen")
}

data object Graph{
    val main="main_graph"
    val auth="auth_graph"
    val root="root_graph"
}
