package com.example.moviestreamerapp.stateEventControl

sealed interface AuthEvent {
    data class OnEmailChange(val email: String): AuthEvent
    data class OnPasswordChange(val password: String): AuthEvent
    data object OnSignUp: AuthEvent
    data object OnSignIn: AuthEvent
}