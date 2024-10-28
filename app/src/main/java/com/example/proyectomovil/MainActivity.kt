package com.example.proyectomovil

import CandidatesList
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
        composable(
            "candidatesReviewPage?respuesta1={respuesta1}&respuesta2={respuesta2}&respuesta3={respuesta3}&respuesta4={respuesta4}&respuesta5={respuesta5}",
            arguments = listOf(
                navArgument("respuesta1") { defaultValue = "" },
                navArgument("respuesta2") { defaultValue = "" },
                navArgument("respuesta3") { defaultValue = "" },
                navArgument("respuesta4") { defaultValue = "" },
                navArgument("respuesta5") { defaultValue = "" }
            )
        ) { backStackEntry ->
            OutlinedCardExample(
                navController = navController,
                respuesta1 = backStackEntry.arguments?.getString("respuesta1"),
                respuesta2 = backStackEntry.arguments?.getString("respuesta2"),
                respuesta3 = backStackEntry.arguments?.getString("respuesta3"),
                respuesta4 = backStackEntry.arguments?.getString("respuesta4"),
                respuesta5 = backStackEntry.arguments?.getString("respuesta5")
            )
        }
        composable("candidatesForm") {
            Form(navController = navController)
        }
    }
}
