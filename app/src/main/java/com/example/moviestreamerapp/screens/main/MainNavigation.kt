package com.example.moviestreamerapp.screens.main

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.Navigation
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.moviestreamerapp.navigation.Graph
import com.example.moviestreamerapp.navigation.NavRoutes

fun NavGraphBuilder.mainNavigation(modifier: Modifier,navController: NavController){
    navigation(startDestination = NavRoutes.Home.route,route= Graph.main){
        composable(route= NavRoutes.Home.route){
            HomeScreen()
        }
    }
}