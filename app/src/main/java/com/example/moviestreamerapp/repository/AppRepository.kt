package com.example.moviestreamerapp.repository

import com.example.moviestreamerapp.general.Resource
import com.example.moviestreamerapp.navigation.Graph.auth
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AppRepository @Inject constructor(
    val auth: FirebaseAuth
) {

    fun createUser(email: String,password: String): Flow<Resource<Unit>> = flow{
        emit(Resource.Loading)


        try {
            auth.createUserWithEmailAndPassword(email,password).await()
            emit(Resource.Success(Unit))

        } catch (e: Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}