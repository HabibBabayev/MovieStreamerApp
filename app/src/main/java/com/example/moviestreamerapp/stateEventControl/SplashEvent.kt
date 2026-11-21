package com.example.moviestreamerapp.stateEventControl

sealed interface SplashEvent {
    data class onLoginCheck(val token:String?): SplashEvent
}