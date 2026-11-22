package com.example.moviestreamerapp.screens.auth

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.moviestreamerapp.navigation.Graph
import com.example.moviestreamerapp.navigation.NavGraph
import com.example.moviestreamerapp.navigation.NavRoutes
import kotlinx.coroutines.delay

fun NavGraphBuilder.authNavigation(modifier: Modifier,navController: NavController){
    navigation(startDestination = NavRoutes.AuthEntry.route, route = Graph.auth){
        composable(route = NavRoutes.AuthEntry.route) {
            AuthEntryScreen(modifier=modifier.padding(horizontal = 24.dp),{
                navController.popBackStack()
            }, onSignInClick ={
                navController.navigate(NavRoutes.Login.route)
            }, onSignUpClick = {
                navController.navigate(NavRoutes.SignUp.route)
            } )
        }
        composable(route = NavRoutes.Login.route) {
            val viewModel: AuthViewModel= hiltViewModel()
            val state by viewModel.uiState.collectAsStateWithLifecycle()
            val context=LocalContext.current
LaunchedEffect(state.isLoggedIn) {
    if (state.isLoggedIn) navController.navigate(route= NavRoutes.Home.route) {
        popUpTo(route = NavRoutes.AuthEntry.route, popUpToBuilder = { inclusive = true })
        popUpTo(route = NavRoutes.Login.route, popUpToBuilder = { inclusive = true })
        Toast.makeText(context, "successfully signed in", Toast.LENGTH_LONG).show()
    }}
            LoginScreen(modifier=modifier.padding(horizontal = 24.dp), onBackClick = {
                navController.popBackStack()
            }, onSignUpClick ={navController.navigate(NavRoutes.SignUp.route)}, onForgotClick = {navController.navigate(
                NavRoutes.ForgotPassword.route)},
                state = state, event =viewModel::onEvent)
        }
        composable(route = NavRoutes.SignUp.route) {
            val viewModel: AuthViewModel= hiltViewModel()
            val state by viewModel.uiState.collectAsStateWithLifecycle()
            val context=LocalContext.current
            LaunchedEffect(state.isSignedUp) {
                if (state.isSignedUp) navController.navigate(route = NavRoutes.Login.route)
                delay(1000)
                Toast.makeText(context,"succesfully signed up", Toast.LENGTH_LONG).show()
            }
            SignUpScreen(modifier.padding(horizontal = 24.dp), onBackClick = {
                navController.popBackStack()
            }, onSignInClick = {navController.navigate(NavRoutes.Login.route)}, onProfileClick = {}, state = state, event = viewModel::onEvent)
        }
        composable(route = NavRoutes.ForgotPassword.route){
            ForgotPasswordScreen(modifier.padding(24.dp), onBackClick = {navController.popBackStack()},4)
        }
        composable(route= NavRoutes.Profile.route) {
            ProfileScreen(modifier.padding(horizontal = 24.dp), onBackClick = {navController.popBackStack()})
        }
        composable(route = NavRoutes.NewPassword.route){
            NewPasswordScreen(modifier.padding(horizontal = 24.dp), onBackClick = {
                navController.popBackStack()
            })
        }
    }
}