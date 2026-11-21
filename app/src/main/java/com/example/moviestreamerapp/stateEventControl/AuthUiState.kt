package com.example.moviestreamerapp.stateEventControl

data class AuthUiState(
    val email: String="",
    val password: String="",
    val isLoggedIn: Boolean=false,
    val error: Boolean=false,
    val loading: Boolean=false,
    val errorMessage: String=""
)