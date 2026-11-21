package com.example.moviestreamerapp.screens.root

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.moviestreamerapp.R
import com.example.moviestreamerapp.navigation.Graph
import com.example.moviestreamerapp.navigation.NavRoutes
import com.example.moviestreamerapp.ui.theme.OverlayColor
import com.example.moviestreamerapp.ui.theme.OverlayColor2
import com.example.moviestreamerapp.ui.theme.OverlayColor3
import com.example.moviestreamerapp.ui.theme.PrimaryBlack
import kotlinx.coroutines.delay


fun NavGraphBuilder.rootNavigation(modifier: Modifier,navController: NavController){
    navigation(startDestination = NavRoutes.Splash.route, route= Graph.root, ){
        composable(route = NavRoutes.Splash.route){

            SplashScreen(modifier = modifier.padding(24.dp))
            LaunchedEffect(Unit) {
                delay(5000)
                navController.navigate(route= NavRoutes.Intro.route){
                    popUpTo(route= NavRoutes.Splash.route, popUpToBuilder = {
                        inclusive=true
                    })
                }
            }
        }
        composable(route = NavRoutes.Intro.route) {

            IntroScreen(modifier = modifier.padding(24.dp), onAuthClick = {
                navController.navigate(route= Graph.auth){
                    popUpTo (route= NavRoutes.Intro.route, popUpToBuilder = {
                        inclusive=true
                    } )
                }
            })
        }
    }
}