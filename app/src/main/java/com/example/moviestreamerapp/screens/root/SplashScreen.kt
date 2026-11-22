package com.example.moviestreamerapp.screens.root

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.moviestreamerapp.R
import com.example.moviestreamerapp.ui.theme.AnimateChanges
import com.example.moviestreamerapp.ui.theme.PrimaryBlack
import com.example.moviestreamerapp.ui.theme.PrimaryRed
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(modifier: Modifier){


    Column(modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Column(Modifier.fillMaxHeight().weight(1f),
            verticalArrangement = Arrangement.Center
            ) {



                    Icon(imageVector = ImageVector.vectorResource(R.drawable.movie_logo),
                        contentDescription = "movie_logo",
                        Modifier.size(118.dp)
                        ,
                        tint = Color.Unspecified)

            }

        Column(modifier.fillMaxWidth().
        fillMaxHeight(0.08f)
            ,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
            AnimatedVisibility(visible=true) {
                CircularProgressIndicator(color = PrimaryRed, modifier = Modifier.size(36.dp),)
            }
        }
}}



