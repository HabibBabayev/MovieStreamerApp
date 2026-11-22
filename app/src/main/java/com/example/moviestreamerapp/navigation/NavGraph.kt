package com.example.moviestreamerapp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.moviestreamerapp.screens.auth.authNavigation
import com.example.moviestreamerapp.screens.main.mainNavigation
import com.example.moviestreamerapp.screens.root.rootNavigation

@Composable
fun NavGraph(modifier: Modifier,navController: NavHostController){
    NavHost(navController = navController, startDestination = Graph.root){
        rootNavigation(Modifier,navController=navController)
        authNavigation(modifier=modifier,navController=navController)
        mainNavigation(modifier=modifier,navController=navController)
    }
}