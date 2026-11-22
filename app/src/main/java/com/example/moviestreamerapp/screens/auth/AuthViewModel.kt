package com.example.moviestreamerapp.screens.auth

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.moviestreamerapp.general.BaseViewModel
import com.example.moviestreamerapp.general.Resource
import com.example.moviestreamerapp.repository.AppRepository
import com.example.moviestreamerapp.stateEventControl.AuthEvent
import com.example.moviestreamerapp.stateEventControl.AuthUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
val repository: AppRepository
) : BaseViewModel<AuthUiState, AuthEvent>() {
    override val initialState: AuthUiState
        get() = AuthUiState()
    override fun onEvent(event: AuthEvent) {
        when(event){
           is AuthEvent.OnEmailChange->{
                updateState { it.copy(
                    email = event.email
                ) } }
            is AuthEvent.OnPasswordChange->{
                updateState {it.copy(
                    password = event.password
                )} }
            is AuthEvent.OnSignUp->{
                getAuthorization()
            }
            is AuthEvent.OnSignIn->{
                loginUser()
            }
        }
    }


  private fun getAuthorization(){
        viewModelScope.launch {
            val state=getUiState()
            repository.createUser(state.email,state.password).collect {response->
                when(response){
                   is Resource.Loading->{
                       Log.e("ugurlu","basladiq")
                        updateState { it.copy(
                            loading = true,
                            error = false,
                            isLoggedIn = false
                        ) }
                    }
                   is Resource.Success->{
                       Log.e("ugurlu","siz daxil oldunuz")
                        updateState {
                            it.copy(
                                isSignedUp = true,
                                loading = false,
                                error = false
                            )
                        }
                    }
                    is Resource.Error->{
                        updateState {
                            it.copy(
                                isSignedUp = false,
                                loading = false,
                                error = true,
                                errorMessage = response.message
                            )
                        }
                    }
                }
            }
        }
    }
    private fun loginUser(){
        viewModelScope.launch {
            val state=getUiState()
            repository.login(state.email,state.password).collect { response->
                when(response){
                    is Resource.Loading->{

                        updateState {
                            it.copy(
                                loading = true,
                                error = false,
                                isLoggedIn = false
                            )
                        }
                    }
                    is Resource.Success->{

                        updateState {
                            it.copy(
                                isLoggedIn = true,
                                error = false,
                                loading = false
                            )
                        }
                    }
                    is Resource.Error->{
                        Log.e("ugurlu",response.message)
                        updateState {
                            it.copy(
                                error = true,
                                errorMessage = response.message,
                                loading = false,
                                isLoggedIn = false
                            )
                        }
                    }
                }
            }
        }
    }
    fun checkAuth(){
        val state=getUiState()
        if(repository.auth.currentUser==null){
            updateState {
                it.copy(
                    isLoggedIn = false
                )
            }
        } else {

            updateState {
                it.copy(
                    isLoggedIn = true
                )
            }
        }
    }
    init {
//        checkAuth()
    }
}