package com.example.moviestreamerapp.general

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<state,event>: ViewModel() {
    protected abstract val initialState:state
    private val _uiState= MutableStateFlow(initialState)
    val uiState get() = _uiState.asStateFlow()

    abstract fun onEvent(event:event)

    fun updateState(stateUpdate:(state)->state){
        _uiState.update (stateUpdate)
    }
    protected fun getUiState():state=_uiState.value
}