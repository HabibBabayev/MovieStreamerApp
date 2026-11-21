package com.example.moviestreamerapp.screens.auth

import android.widget.Toast
import androidx.compose.foundation.layout.padding
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
            LoginScreen(modifier=modifier.padding(horizontal = 24.dp), onBackClick = {
                navController.popBackStack()
            }, onSignUpClick ={navController.navigate(NavRoutes.SignUp.route)}, onForgotClick = {navController.navigate(
                NavRoutes.ForgotPassword.route)} )
        }
        composable(route = NavRoutes.SignUp.route) {
            val viewModel: SignUpViewModel= hiltViewModel()
            val state by viewModel.uiState.collectAsStateWithLifecycle()
            val context=LocalContext.current
            SignUpScreen(modifier.padding(horizontal = 24.dp), onBackClick = {
                navController.popBackStack()
            }, onSignInClick = {navController.navigate(NavRoutes.Login.route)}, onProfileClick = {
                if (state.isLoggedIn) {
                    navController.navigate(NavRoutes.Profile.route)
                    Toast.makeText(context,"succesfull attempt", Toast.LENGTH_LONG)
                } else Toast.makeText(context,"Unsuccesfull attempt", Toast.LENGTH_LONG)
            }, state = state, event = viewModel::onEvent)
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