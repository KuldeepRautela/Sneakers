package com.example.sneakersapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sneakersapp.ui.theme.composables.Cart
import com.example.sneakersapp.ui.theme.composables.SneakerDashboard
import com.example.sneakersapp.ui.theme.composables.SneakerDetails
import com.example.sneakersapp.viewmodels.SneakerViewModel

@Composable
fun SneakerNavigation(viewModel: SneakerViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "SneakerDashboard") {
        composable("SneakerDashboard") {
            SneakerDashboard(viewModel,navController)
        }
        composable("SneakerDetails") {
            SneakerDetails(viewModel)
        }
        composable("Cart") {
            Cart(viewModel)
        }
    }
}


