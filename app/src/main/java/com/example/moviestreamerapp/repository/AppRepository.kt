package com.example.moviestreamerapp.repository

import android.util.Log
import com.example.moviestreamerapp.general.Resource
import com.example.moviestreamerapp.navigation.Graph
import com.example.moviestreamerapp.navigation.Graph.auth
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AppRepository @Inject constructor(
    val auth: FirebaseAuth
) {

    fun createUser(email: String,password: String): Flow<Resource<Unit>> = flow{
        emit(Resource.Loading)


        try {
         val result= auth.createUserWithEmailAndPassword(email,password).await()
            val user=result.user
            result.user?.reload()?.await()
            if (user?.isEmailVerified==true){
                emit(Resource.Success(Unit))
            } else auth.signOut()




        } catch (e: Exception){
            Log.e("firebase",e.message.toString())
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
    fun login(email: String,password: String): Flow<Resource<Unit>> =flow {
        emit(Resource.Loading)

        try {
           val response= auth.signInWithEmailAndPassword(email,password).await()
           emit(Resource.Success(Unit))
        } catch (e: Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}