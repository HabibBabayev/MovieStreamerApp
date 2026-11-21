package com.example.moviestreamerapp.screens.root

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.moviestreamerapp.repository.AppRepository
import com.example.moviestreamerapp.stateEventControl.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    val rep: AppRepository
): ViewModel() {

}