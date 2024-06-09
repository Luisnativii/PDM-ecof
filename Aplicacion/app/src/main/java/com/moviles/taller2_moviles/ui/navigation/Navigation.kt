package com.moviles.taller2_moviles.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.moviles.taller2_moviles.MainViewModel
import com.moviles.taller2_moviles.ui.screen.AddRegister
import com.moviles.taller2_moviles.ui.screen.HomeScreen
import com.moviles.taller2_moviles.ui.screen.RegisterDetails

@Composable
fun Navigation(
    viewModel: MainViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenRoute.Home.route
    ){
        composable(ScreenRoute.Home.route) {
            HomeScreen(viewModel, navController)
        }
        composable(ScreenRoute.AddRegister.route) {
            AddRegister(viewModel, navController)
        }
        composable(ScreenRoute.RegisterDetails.route) { backStackEntry ->
            val familyId = backStackEntry.arguments?.getString("familyId")?.toInt()
            if (familyId != null) {
                RegisterDetails(viewModel, navController, familyId)
            }
        }
    }
}

