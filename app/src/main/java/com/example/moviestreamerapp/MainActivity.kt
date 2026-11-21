package com.example.moviestreamerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.moviestreamerapp.navigation.NavGraph
import com.example.moviestreamerapp.screens.root.IntroScreen
import com.example.moviestreamerapp.ui.theme.MovieStreamerAppTheme
import com.example.moviestreamerapp.ui.theme.PrimaryBlack
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                android.graphics.Color.TRANSPARENT,
                ),
        )
        setContent {
            val navController= rememberNavController()
            MovieStreamerAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    containerColor = PrimaryBlack
                    ) { innerPadding ->
                    NavGraph(modifier = Modifier.padding(innerPadding)
                        ,navController=navController)
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController= rememberNavController()
    MovieStreamerAppTheme {
//        IntroScreen(modifier = Modifier)
    }
}