package com.example.proyectomovil

import CandidatesList
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectomovil.ui.theme.ProyectoMovilTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProyectoMovilTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "candidatesList") {
        composable("candidatesList") {
            CandidatesList(navController = navController)
        }
        composable("candidatesReviewPage") {
            OutlinedCardExample(navController = navController)
        }
        composable("candidatesForm") { 
            Form(navController = navController)
        }
    }
}